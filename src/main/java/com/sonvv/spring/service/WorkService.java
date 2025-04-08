package com.sonvv.spring.service;

import com.sonvv.spring.model.Work;
import com.sonvv.spring.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    public Work createWork(Work work) {
        return workRepository.save(work);
    }

    public Work findWorkById(Long id) {
        Optional<Work> optional = workRepository.findById(id);
        return optional.orElse(null);
    }

    public Work updateWork(Work work) {
        return workRepository.save(work);
    }

    public boolean deleteWork(Long id) {
        if (workRepository.existsById(id)) {
            workRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
