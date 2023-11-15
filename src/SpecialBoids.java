import java.awt.*;
public class SpecialBoids extends Boids{
    private String name;

    private Color color;
    public SpecialBoids(int x, int y, int vx, int vy, int orientation, int taille_x, int taille_y, Color color, String name){
        super(x, y, vx, vy,orientation, taille_x, taille_y);
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }


    public void separate(SpecialBoids[] list_boids, int distance_separation) {
        // RULE 2 Keeping a small distance between boids
        // ON PEUT CHANGER LES PARAM DE LA REGLE AVEC LA COULEUR
        if(this.name.equals("poisson")){
            int[] VectorC = new int[]{0, 0};
            int dx = 0;
            int dy = 0;
            for (SpecialBoids boid : list_boids) {
                if (boid.getPosition() != this.position && boid.name.equals(this.name)) {
                    int dist = distance(boid);
                    if (dist < distance_separation) {
                        dx += (position[0] - boid.position[0]) / 14;
                        dy += (position[1] - boid.position[1]) / 14;
                        // on les rajoute
                    }
                } else if(boid.getPosition() != this.position && boid.name.equals("requin")){
                    // un poisson est proche d'un requin IL FAUT PARTIR !!!!
                    int dist = distance(boid);
                    if (dist < distance_separation) {
                        dx += (position[0] - boid.position[0]) / 4;
                        dy += (position[1] - boid.position[1]) / 4;
                        // on les rajoute
                    }
                }
            }
            VectorC[0] = dx;
            VectorC[1] = dy;
            update_vitesse(VectorC);
        } else if(this.name.equals("requin")) {
            // on fais rien on veut juste aller manger les poissons
        }

    }

    public void align(SpecialBoids[] boids, int distance_alignement) {
        // RULE 3 matching nearest neighboors velocity
        // ON PEUT CHANGER LES PARAM DE LA REGLE AVEC LA COULEUR
        if(this.name.equals("poisson")){
            int[] VectorPvj = new int[]{0, 0};
            int count = 0;
            for (SpecialBoids boid : boids) {
                if (boid.getPosition() != this.position && boid.name.equals(this.name)) {
                    int dist = distance(boid);
                    if (dist < distance_alignement) {
                        VectorPvj[0] += boid.vitesse[0];
                        VectorPvj[1] += boid.vitesse[1];
                        count++;
                    }
                }
            }
            if (count > 0) {
                VectorPvj[0] /= count;
                VectorPvj[1] /= count;
                // on soustrait bj velocity
                VectorPvj[0] -= vitesse[0];
                VectorPvj[1] -= vitesse[1];
                // on divise par 8
                VectorPvj[0] /= 8;
                VectorPvj[1] /= 8;

                update_vitesse(VectorPvj);
            }
        } else if(this.name.equals("requin")) {
            // on fais rien on veut juste aller manger les poissons
        }

    }

    public void cohere(SpecialBoids[] boids, int distance_essaim) {
        // RULE 1 go to the center of mass
        // ON PEUT CHANGER LES PARAM DE LA REGLE AVEC LA COULEUR
        if(this.name.equals("poisson")){
            int[] VectorPcj = new int[]{0, 0};
            int count = 0;
            for (SpecialBoids boid : boids) {
                if (boid.getPosition() != this.position && boid.name.equals(this.name)) {
                    int dist = distance(boid);
                    if (dist > 0 && dist < distance_essaim) {
                        VectorPcj[0] += boid.position[0];
                        VectorPcj[1] += boid.position[1];
                        count++;
                    }
                }
            }
            if (count > 0) {
                VectorPcj[0] /= count;
                VectorPcj[1] /= count;
                // on soustrait
                VectorPcj[0] -= position[0];
                VectorPcj[1] -= position[1];
                // on divise par 100
                VectorPcj[0] /= 60;
                VectorPcj[1] /= 60;

                update_vitesse(VectorPcj);
            }
        } else if(this.name.equals("requin")) {
            // ON FONCE VERS LES POISSONS
            int[] VectorPcj = new int[]{0, 0};
            int count = 0;
            for (SpecialBoids boid : boids) {
                if (boid.getPosition() != this.position && boid.name.equals("poisson")) {
                    int dist = distance(boid);
                    if (dist > 0 && dist < distance_essaim) {
                        VectorPcj[0] += boid.position[0];
                        VectorPcj[1] += boid.position[1];
                        count++;
                    }
                }
            }
            if (count > 0) {
                VectorPcj[0] /= count;
                VectorPcj[1] /= count;
                // on soustrait
                VectorPcj[0] -= position[0];
                VectorPcj[1] -= position[1];
                // on divise par 100
                VectorPcj[0] /= 10;
                VectorPcj[1] /= 10;

                update_vitesse(VectorPcj);
            }
        }

    }
}
