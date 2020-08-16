package com.lxseason.bootlaunch.model.yaml;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Father {
    private String name;
    @Min(0)
    private Integer age;
}
