package com.itsdcode.feed.domain.dto.md;

import com.itsdcode.feed.auditor.AuditorBaseEntity;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import lombok.*;

import javax.persistence.*;

/**
 *  Feed 관련 객체가 아니기에 구조중 어디에 속할지 불분명
 *  추후 전체 구조를 알게 됬을 때 배치
 */
@Entity
@Table
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "feedDetail")
@AttributeOverride(name = "id" , column = @Column(name = "MDInfo_Id"))
public class MDInfo  extends AuditorBaseEntity {

    @Getter
    @Column(nullable = false , length = 200)
    private String mdName;

    @Getter
    @Column(nullable = false , length = 200)
    private String mdThumb;

    @Getter
    @Column(length = 1000)
    private String mdDesc;

//    @OneToOne(mappedBy = "mdInfo")
//    private FeedDetail feedDetail;

}

