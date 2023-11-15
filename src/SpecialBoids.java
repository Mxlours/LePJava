import java.awt.*;
/**
 * SpecialBoids class extends the Boids class and represents a special type of boid.
 * It includes additional properties such as name and color.
 * The class provides methods for separating boids, aligning boids, and coherring boids based on their name and distance parameters.
 */
public class SpecialBoids extends Boids{
    private String name;

    private Color color;
    /**
     * Constructor for SpecialBoids class.
     * Initializes the special boid with the given position, velocity, orientation, size, color, and name.
     *
     * @param x             the x-coordinate of the boid's position
     * @param y             the y-coordinate of the boid's position
     * @param vx            the x-component of the boid's velocity
     * @param vy            the y-component of the boid's velocity
     * @param orientation   the orientation of the boid
     * @param taille_x      the x-size of the boid
     * @param taille_y      the y-size of the boid
     * @param color         the color of the boid
     * @param name          the name of the boid
     */
    public SpecialBoids(int x, int y, int vx, int vy, int orientation, int taille_x, int taille_y, Color color, String name){
        super(x, y, vx, vy,orientation, taille_x, taille_y);
        this.color = color;
        this.name = name;
    }
    /**
     * Returns the color of the special boid.
     *
     * @return The color of the special boid.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Returns the name of the special boid.
     *
     * @return The name of the special boid.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Separates the special boids from other boids based on the distance separation parameter.
     * If the special boid is a "poisson", it adjusts its velocity based on the distance to other "poisson" boids.
     * If the special boid is a "requin", it does not perform any separation.
     *
     * @param list_boids             an array of boids
     * @param distance_separation    the distance threshold for separation
     */
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
    /**
     * Aligns the special boids with other boids based on the distance alignment parameter.
     * If the special boid is a "poisson", it adjusts its velocity to match the average velocity of nearby "poisson" boids.
     * If the special boid is a "requin", it does not perform any alignment.
     *
     * @param boids                 an array of boids
     * @param distance_alignement    the distance threshold for alignment
     */
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
    /**
     * Cohers the special boids towards the center of mass of nearby boids based on the distance cohesion parameter.
     * If the special boid is a "poisson", it adjusts its velocity towards the center of mass of nearby "poisson" boids.
     * If the special boid is a "requin", it adjusts its velocity towards the center of mass of nearby "poisson" boids.
     *
     * @param boids             an array of boids
     * @param distance_essaim   the distance threshold for cohesion
     */
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
