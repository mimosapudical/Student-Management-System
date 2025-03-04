package com.example.student_management.service;

import com.example.student_management.Dao.UniversityClassDao;
import com.example.student_management.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.student_management.expections.InvalidUniversityClassException;



import java.util.Calendar;
import java.util.List;

@Service
public class UniversityClassService {

    UniversityClassDao universityClassDao;

    @Autowired
    public UniversityClassService(UniversityClassDao universityClassDao) {
        this.universityClassDao = universityClassDao;
    }

    public List<UniversityClass> getAllClasses() {
        return (List<UniversityClass>) universityClassDao.findAll();
    }

    public UniversityClass addClass(UniversityClass universityClass) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (universityClass.getYear() < currentYear) {
            throw new InvalidUniversityClassException("Cannot add class in the past");
        }
        if (universityClass.getYear() > currentYear + 1) {
            throw new InvalidUniversityClassException("Cannot add class in the far future");
        }
        return universityClassDao.save(universityClass);
    }
}
