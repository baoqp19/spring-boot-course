package vn.hoidanit.jobhunter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Skill;
import vn.hoidanit.jobhunter.domain.respone.ResultPaginationDTO;
import vn.hoidanit.jobhunter.repository.SkillRepository;

@Service
// đây là 1 service để thực hiện login nghiệp vụ liên quan đến skill a
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    public boolean isNameExist(String name){
        return this.skillRepository.existsByName(name);
    }

    public Skill fetchSkillById(long id){
        // optional là chứa các phương thức giúp kiểm tra xem các giá trị không empty() orElse()...
        Optional<Skill> skillOptional = this.skillRepository.findById(id);
        if(skillOptional.isPresent()){
            return skillOptional.get();
        }
        return null;
    }

    public Skill createSkill(Skill s){
        return this.skillRepository.save(s);
    }


    public Skill updateSkill(Skill s){
        return this.skillRepository.save(s);
    }

    public void deleteSkill(long id){
       // delete job (inside job_skill table)
        Optional<Skill> skillOptional = this.skillRepository.findById(id);
        Skill currentSkill = skillOptional.get();
        currentSkill.getJobs().forEach(job -> job.getSkills().remove(currentSkill));

        // delete skill
        this.skillRepository.delete(currentSkill);
    }

    public ResultPaginationDTO fetchAllSkills(Specification<Skill> spec, Pageable pageable) {
        Page<Skill> pageUser = this.skillRepository.findAll(spec, pageable);

        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pageUser.getTotalPages());
        mt.setTotal(pageUser.getTotalElements());

        rs.setMeta(mt);

        rs.setResult(pageUser.getContent());

        return rs;
    }



}
