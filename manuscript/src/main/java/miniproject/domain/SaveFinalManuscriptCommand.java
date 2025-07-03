package miniproject.domain;

import lombok.Data;

// 원고 최종 저장
@Data
public class SaveFinalManuscriptCommand {

    // 기존 데이터를 기반으로 하므로 id만 있으면 됨 (어떤 원고를 최종 저장할 것인지)
    private Long manuscriptId;
}
