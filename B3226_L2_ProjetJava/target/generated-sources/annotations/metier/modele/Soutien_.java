package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Matiere;
import metier.modele.Soutien.Etat;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-26T20:13:10")
@StaticMetamodel(Soutien.class)
public class Soutien_ { 

    public static volatile SingularAttribute<Soutien, String> feedback;
    public static volatile SingularAttribute<Soutien, Byte> note;
    public static volatile SingularAttribute<Soutien, Date> dateDebut;
    public static volatile SingularAttribute<Soutien, String> details;
    public static volatile SingularAttribute<Soutien, Long> id;
    public static volatile SingularAttribute<Soutien, Date> dateFin;
    public static volatile SingularAttribute<Soutien, Eleve> eleve;
    public static volatile SingularAttribute<Soutien, Date> dateDemande;
    public static volatile SingularAttribute<Soutien, Etat> etat;
    public static volatile SingularAttribute<Soutien, Intervenant> intervenant;
    public static volatile SingularAttribute<Soutien, Matiere> matiere;

}