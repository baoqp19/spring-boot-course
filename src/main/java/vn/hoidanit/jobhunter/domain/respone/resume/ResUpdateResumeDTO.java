package vn.hoidanit.jobhunter.domain.respone.resume;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUpdateResumeDTO {
    private Instant updatedAt;
    private String updatedBy;
}