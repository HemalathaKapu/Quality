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
@Table(name= "inspection", schema = "quality")
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "Inspection")
    @TableGenerator(name = "Inspection",table = "t_idgenerator",initialValue = 100)
    private Integer id;
    private String inspectorName;
    private String status;
    private String remarks;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fg")
    private FinishedGood fg;

    @OneToMany(mappedBy = "inspection",cascade = CascadeType.REFRESH)
    private Set<InspectionDetails> inspectionDetails = new LinkedHashSet<>();

}
