package org.siemens.quality.dao.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "fg_type_master", schema = "quality")
public class FgTypeMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "FgTypeMaster")
    @TableGenerator(name = "FgTypeMaster",table = "t_idgenerator",initialValue = 100)
    private Integer id;
    private String fgType;
    @OneToMany(mappedBy = "fgType",cascade = CascadeType.REFRESH)
    private Set<FgTypeMapper> fgTypeMappers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fgType",cascade = CascadeType.REFRESH)
    private Set<FinishedGood> finishedGoods = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fgType",cascade = CascadeType.REFRESH)
    private Set<Measurement> measurements = new LinkedHashSet<>();
}
