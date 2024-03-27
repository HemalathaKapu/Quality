package org.siemens.quality.dao.entites;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "inspection_details", schema = "quality")
public class InspectionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "InspectionDetails")
    @TableGenerator(name = "InspectionDetails",table = "t_idgenerator",initialValue = 100)
    private Integer id;
    private String value;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement")
    private Measurement measurement;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection")
    private Inspection inspection;
}
