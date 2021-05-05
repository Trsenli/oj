#!/usr/bin/python
#! -*- coding: utf8 -*-

import lorun
import os

RESULT_STR = [
    'Accepted',
    'Presentation Error',
    'Time Limit Exceeded',
    'Memory Limit Exceeded',
    'Wrong Answer',
    'Runtime Error',
    'Output Limit Exceeded',
    'Compile Error',
    'System Error'
]

bitFileName=['./m']
tmpFileName='./m'

def compileSrc(src_path):
    global tmpFileName
    codeType=src_path.split(".")[1].lower()
    if codeType == 'c':
        if os.system('gcc %s -o m'%src_path) != 0:# 加入javac java/g++ -std=11/python
            print("{'result': 'Compile Error'}")
            return False
    elif codeType == 'cpp':
        if os.system('g++ %s -std=11 -o m'%src_path) != 0:
            print("{'result': 'Compile Error'}")
            return False
    elif codeType == 'python':
        bitFileName[0] = 'python3'
        bitFileName.append(src_path.split('.')[0])
        if os.system('javac %s' % src_path) != 0:
            print("{'result': 'Compile Error'}")
            return False
    return True

def runone(in_path, out_path):
    fin = open(in_path)
    ftemp = open('temp.out', 'w')

    runcfg = {
        'args':bitFileName,
        'fd_in':fin.fileno(),
        'fd_out':ftemp.fileno(),
        'timelimit':1000, #in MS
        'memorylimit':20000, #in KB
    }

    rst = lorun.run(runcfg)
    fin.close()
    ftemp.close()

    if rst['result'] == 0:
        ftemp = open('temp.out')
        fout = open(out_path)
        crst = lorun.check(fout.fileno(), ftemp.fileno())
        fout.close()
        ftemp.close()
        os.remove('temp.out')
        if crst != 0:
            return {'result':crst}

    return rst

def judge(src_path, td_path, td_total):
    if not compileSrc(src_path):
        return
    for i in range(td_total):
        in_path = os.path.join(td_path, '%d.in'%i)
        out_path = os.path.join(td_path, '%d.out'%i)
        if os.path.isfile(in_path) and os.path.isfile(out_path):
            rst = runone(in_path, out_path)
            rst['result'] = RESULT_STR[rst['result']]
            print(rst)
        else:
            print('testData:%d incomplete' % i)
            if bitFileName[0]!='python3':
                os.remove(tmpFileName)
            exit(-1)
    os.remove(tmpFileName)

if __name__ == '__main__':
    import sys
    if len(sys.argv) != 4:
        print('Usage:%s srcfile testData_pth testData_total')
        exit(-1)
    judge(sys.argv[1], sys.argv[2], int(sys.argv[3]))
