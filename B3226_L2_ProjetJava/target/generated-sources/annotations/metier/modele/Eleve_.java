package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Ecole;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-26T20:13:10")
@StaticMetamodel(Eleve.class)
public class Eleve_ extends Personne_ {

    public static volatile SingularAttribute<Eleve, Date> naissance;
    public static volatile SingularAttribute<Eleve, Integer> classe;
    public static volatile SingularAttribute<Eleve, Ecole> ecole;

}