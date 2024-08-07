package com.example.demo.service;

import com.example.demo.entity.DeGiay;
import com.example.demo.repository.DeGiayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface DeGiayService {

    List<DeGiay> findAll();

    DeGiay add(DeGiay deGiay);

    DeGiay findById(Integer id);

    void delete(Integer id);

    List<DeGiay> getDeGiayByTen(String ten);


}
