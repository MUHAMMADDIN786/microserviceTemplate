package com.fastech.systems.employeservice.service;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.SkillDto;
import com.fastech.systems.employeservice.model.Skill;
import com.fastech.systems.employeservice.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    @Autowired
    private SkillRepository repository;
    @Autowired
    private SkillTypeService skillTypeService;
    public Page<Skill> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Skill create(SkillDto dto) throws Exception {

        if(dto.getSkillName().isBlank()){
            throw new Exception("Please Enter Valid skill name");
        }
        Skill entity= new Skill();
        entity.setSkillName(dto.getSkillName());
        entity.setSkillDescription(dto.getSkillDescription());
        if(dto.getSkillTypeId()!=null){
            entity.setSkillType(skillTypeService.findById(dto.getSkillTypeId()));
        }

        return repository.save(entity);
    }
    public Skill findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("Skill not found for id = "+id));
    }
    public Skill update(SkillDto dto) throws Exception {

        if (dto.getSkillID() == null)
            throw new Exception("Please mention skill Id");
        Skill entity=repository.findById(dto.getSkillID()).orElseThrow(()-> new Exception("skill not found for id = "+dto.getSkillID()));

        if(!dto.getSkillName().isBlank()){
            entity.setSkillName(dto.getSkillName());
        }
        if(!dto.getSkillDescription().isBlank()){
            entity.setSkillDescription(dto.getSkillDescription());
        }
        if(dto.getSkillTypeId()!=null){
            entity.setSkillType(skillTypeService.findById(dto.getSkillTypeId()));
        }
        return repository.save(entity);
    }
    public Skill removeUser(Integer id) throws Exception {
        Skill entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
