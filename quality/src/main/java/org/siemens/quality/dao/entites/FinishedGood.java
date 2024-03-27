package org.siemens.quality.dao.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "finished_goods", schema = "quality")
public class FinishedGood {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "FinishedGoods")
    @TableGenerator(name = "FinishedGoods",table = "t_idgenerator",initialValue = 100)
    private Integer id;
    private String subOperation;
    private String matCode;
    private String matDescription;
    private String fgId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fg_type")
    private FgTypeMaster fgType;

    @OneToMany(mappedBy = "finishedGood",cascade = CascadeType.REFRESH)
    private Set<ProcessParameters> processParameters = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fg",cascade = CascadeType.REFRESH)
    private Set<Inspection> inspections = new LinkedHashSet<>();
}
