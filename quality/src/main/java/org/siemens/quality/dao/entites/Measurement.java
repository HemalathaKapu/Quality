package org.siemens.quality.dao.entites;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "measurements", schema = "quality")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "Measurements")
    @TableGenerator(name = "Measurements",table = "t_idgenerator",initialValue = 100)
    private Integer id;
    private String name;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fg_type")
    private FgTypeMaster fgType;

    @OneToMany(mappedBy = "measurement",cascade = CascadeType.REFRESH)
    private Set<InspectionDetails> inspectionDetails = new LinkedHashSet<>();


}
