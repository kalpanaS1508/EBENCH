package com.ebench.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobResponse {


    public String jobTitle;
    public String company_name;
    public String experience;
    public String jobLocation;
    public Date postedDate;
    public String skills;

}
