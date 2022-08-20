package com.ebench.entity;

import com.ebench.Enums.AvailabilityType;
import com.ebench.Enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;


@Entity
@Data
@Valid
@Table(name = "vendor", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping(value = "/vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long vendorId;

    @Column(name = "name")
    public String name;

    @Column(name = "address")
    public String address;

    @Column(name = "email" , unique = true)
    @Valid
    public String email;

    @Column(name = "password")
    public String password;

    @Column(name = "designation")
    public String designation;

    @Column(name = "city")
    public String city;

    @Column(name = "country")
    public String country;

    @Column(name = "status")
    public boolean status ;

    @Column(name = "last_seen")
    @JsonFormat(pattern = "HH:mm:ss")
    public String lastSeen;

    @Column(name = "contact_no")
    public String contactNo;

    @Column(name = "recent_activities")
    public String recentActivities;

    @Column(name = "recent_date_activities")
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date recentDateActivities;

    @Column(name = "daily_activities")
    public String dailyActivities;

    @Column(name = "skype_id")
    public Integer skypeId;

    @Column(name = "twitter_id")
    public Integer twitterId;

    @Column(name = " vendor_profile_image_url")
    public String vendorProfileImageUrl;

    @Column(name = "availability")
    @Enumerated(EnumType.STRING)
    public AvailabilityType availability;

    @Column(name = "experience")
    public String experience;

    @Column(name = "whatsapp")
    public String whatsApp;

    @Column(name = "telegram")
    public String telegram;

    @Column(name = "verification_code" , updatable = true)
    public String verificationCode;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    public UserType userType;
}
