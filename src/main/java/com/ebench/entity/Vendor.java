package com.ebench.entity;

import com.ebench.Constants.AvailabilityType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Valid
@Table(name = "vendor", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

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

    public Vendor() {
    }

    public Vendor(Long vendorId, String name, String address, String email, String password, String designation, String city,
                  String country, boolean status, String lastSeen, String contactNo, String recentActivities,
                  Date recentDateActivities, String dailyActivities, Integer skypeId, Integer twitterId, String vendorProfileImageUrl,
                  AvailabilityType availability, String experience) {
        this.vendorId = vendorId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.designation = designation;
        this.city = city;
        this.country = country;
        this.status = status;
        this.lastSeen = lastSeen;
        this.contactNo = contactNo;
        this.recentActivities = recentActivities;
        this.recentDateActivities = recentDateActivities;
        this.dailyActivities = dailyActivities;
        this.skypeId = skypeId;
        this.twitterId = twitterId;
        this.vendorProfileImageUrl = vendorProfileImageUrl;
        this.availability = availability;
        this.experience = experience;
    }
}
