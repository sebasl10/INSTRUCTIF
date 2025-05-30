package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-30T12:24:11")
@StaticMetamodel(Intervenant.class)
public abstract class Intervenant_ extends Personne_ {

    public static volatile SingularAttribute<Intervenant, String> numTel;
    public static volatile SingularAttribute<Intervenant, Integer> niveauMin;
    public static volatile SingularAttribute<Intervenant, Integer> niveauMax;
    public static volatile SingularAttribute<Intervenant, String> username;

}