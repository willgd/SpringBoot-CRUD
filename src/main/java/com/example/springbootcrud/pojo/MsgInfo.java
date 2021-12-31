package com.example.springbootcrud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MsgInfo {
    private String errorMsg;
    private String successMsg;
    private int exist;
}
