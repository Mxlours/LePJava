import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.List;

public class Boids {
    private int[] position;
    private int[] init_position;
    private int[] init_vitesse;

    private int[] vitesse;
    private int[] acceleration;

    private int[] force;

    private int orientation;

    private int init_orientation;
    public Boids(int x, int y, int vx, int vy, int orientation) {
        position = new int[]{x, y};
        init_position = new int[]{x, y};
        init_vitesse = new int[]{vx, vy};
        init_orientation = orientation;
        vitesse = new int[]{vx, vy};
        acceleration = new int[]{0, 0};
        this.orientation = orientation;
    }
    public int getOrientation() {
    return orientation;
}
    public void reset(){
        this.position = new int[]{init_position[0], init_position[1]};
        this.vitesse = new int[]{init_vitesse[0], init_vitesse[1]};
        this.orientation = init_orientation;
    }

public void setOrientation(int orientation) {
    this.orientation = orientation;
}

    public int[] getPosition() {
        return position;
    }

    public int[] getAcceleration() {
        return acceleration;
    }

    public int[] getVitesse() {
        return vitesse;
    }

    private void update_orientation(){
        // je pense beug ici aussi
        orientation = (int)Math.toDegrees(Math.atan2(vitesse[1], vitesse[0]));
        orientation = (orientation < 0) ? (orientation + 360) : orientation;
    }
    private void update_position() {
        // je pense beug ici j'ai essayé avec cos et sin j'ai pas eu de truc concluant
    position[0] += (int) vitesse[0];
    position[1] += (int) vitesse[1];
}
    private void update_vitesse(int[] vitesse2){
        // update vitesse
        vitesse[0] += vitesse2[0];
        vitesse[1] += vitesse2[1];

    }
    private void update_acceleration(int[] force) {
        // A BANNIR POUR LE MOMENT NE PAS UTILISER
        // je pense que en tout cas dans un premier temps il ne faut pas passer par les accelerations
        // et acceleration et la force n'ont pas les meme unité on peut pas les ajouter (la masse intervient ici)
        acceleration[0] += force[0];
        acceleration[1] += force[1];
    }
    public void update() {
        // changer l'orientation
        // changer position
        update_orientation();
        update_position();
    }


    public int distance(Boids other) {
        int dx = other.position[0] - position[0];
        int dy = other.position[1] - position[1];
        return (int)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public void separate(Boids[] list_boids, int distance_separation) {
        // RULE 2 Keeping a small distance between boids
        int[] VectorC = new int[]{0, 0};
        int dx = 0;
        int dy = 0;
        for (Boids boid : list_boids) {
            if(boid.getPosition() != this.position) {
                int dist = distance(boid);
                if (dist < distance_separation) {
                    dx += position[0] - boid.position[0];
                    dy += position[1] - boid.position[1];
                    // on les rajoute
                }
            }
        }
        VectorC[0] = dx;
        VectorC[1] = dy;
        update_vitesse(VectorC);
    }

    public void align(Boids[] boids, int distance_alignement) {
        // RULE 3 matching nearest neighboors velocity
        int[] VectorPvj = new int[]{0, 0};
        int count = 0;
        for (Boids boid : boids) {
            if(boid.getPosition() != this.position){
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
    }

    public void cohere(Boids[] boids, int distance_essaim) {
        // RULE 1 go to the center of mass
        int[] VectorPcj = new int[]{0, 0};
        int count = 0;
        for (Boids boid : boids) {
            if(boid.getPosition() != this.position){
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
            VectorPcj[0] /= 100;
            VectorPcj[1] /= 100;

            update_vitesse(VectorPcj);
        }
    }
}