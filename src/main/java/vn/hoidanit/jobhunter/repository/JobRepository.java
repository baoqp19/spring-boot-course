package vn.hoidanit.jobhunter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.hoidanit.jobhunter.domain.Job;


@Repository
// nó là 1 kho lưu trữ là các lớp để truy xuất  dữ liệu và thao tác với CSDL (CRUD)
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    
}