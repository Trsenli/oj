package com.oj.ojbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    private String pb_id;
    private String pb_file_id;
    private String pb_title;
    private String pb_description;
    private String pb_input_description;
    private String pb_output_description;
    private String pb_test_case_score;
    private String pb_hint;
    private int pb_time_limit;
    private int pb_memory_limit;
    private String pb_samples;
    private String pb_template;
    private String pb_spj;
    private String pb_source;
    private String pb_answers;
}
