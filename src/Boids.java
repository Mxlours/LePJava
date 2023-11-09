import java.util.ArrayList;
import java.util.List;

public class Boids {
    private int[] position;
    private int[] vitesse;
    private int[] acceleration;

    private int[] force;

    public Boids(int x, int y, int vx, int vy) {
        position = new int[]{x, y};
        vitesse = new int[]{vx, vy};
        acceleration = new int[]{0, 0};
    }

    private void update_position(){
        position[0] += vitesse[0];
        position[1] += vitesse[1];
    }

    private void update_vitesse(){
        vitesse[0] += acceleration[0];
        vitesse[1] += acceleration[1];

    }

    private void update_acceleration(){
        //TODO...
    }
    public void update() {
        update_acceleration();
        update_vitesse();
        update_position();
    }

    public void applyForce(int[] force) {
        acceleration[0] += force[0];
        acceleration[1] += force[1];
    }

    public int distance(Boids other) {
        int dx = other.position[0] - position[0];
        int dy = other.position[1] - position[1];
        return (int)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    // Je suppose dans la question suivante que la liste des Boids ne contient pas le Boid actuel !
    public void separate(List<Boids> list_boids, int distance_separation) {
        int[] totalForce = new int[]{0, 0};
        for (Boids boid : list_boids) {
            int dist = distance(boid);
            if (dist > 0 && dist < distance_separation) {
                int dx = position[0] - boid.position[0];
                int dy = position[1] - boid.position[1];
                int factor = distance_separation / dist;
                totalForce[0] += dx * factor;
                totalForce[1] += dy * factor;
            }
        }
        applyForce(totalForce);
    }

    public void align(List<Boids> boids, int distance_alignement) {
        int[] totalvitesse = new int[]{0, 0};
        int count = 0;
        for (Boids boid : boids) {
            int dist = distance(boid);
            if (dist > 0 && dist < distance_alignement) {
                totalvitesse[0] += boid.vitesse[0];
                totalvitesse[1] += boid.vitesse[1];
                count++;
            }
        }
        if (count > 0) {
            totalvitesse[0] /= count;
            totalvitesse[1] /= count;
            int magnitude = (int)Math.sqrt(totalvitesse[0] * totalvitesse[0] + totalvitesse[1] * totalvitesse[1]);
            if (magnitude > 0) {
                totalvitesse[0] /= magnitude;
                totalvitesse[1] /= magnitude;
            }
            applyForce(totalvitesse);
        }
    }

    public void cohere(List<Boids> boids, int distance_essaim) {
        int[] centre_masse = new int[]{0, 0};
        int count = 0;
        for (Boids boid : boids) {
            int dist = distance(boid);
            if (dist > 0 && dist < distance_essaim) {
                centre_masse[0] += boid.position[0];
                centre_masse[1] += boid.position[1];
                count++;
            }
        }
        if (count > 0) {
            centre_masse[0] /= count;
            centre_masse[1] /= count;
            int dx = centre_masse[0] - position[0];
            int dy = centre_masse[1] - position[1];
            int magnitude = (int)Math.sqrt(dx * dx + dy * dy);
            if (magnitude > 0) {
                dx /= magnitude;
                dy /= magnitude;
            }
            applyForce(new int[]{dx, dy});
        }
    }
}