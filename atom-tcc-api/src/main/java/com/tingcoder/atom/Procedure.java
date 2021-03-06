package com.tingcoder.atom;

import lombok.Data;

import java.io.Serializable;

/***
 * @author yfeng
 * @date 2018-07-05 15:55
 */
@Data
public class Procedure implements Serializable {
    private static final long serialVersionUID = -1777509208773831338L;

    private String service;
    private String version;
    private String group;
    private String method;
    private int index;
}