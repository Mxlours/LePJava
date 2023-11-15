/*
 * Boids.java
 *
 * Summary:
 * This class represents a boid object, which is a simulated bird-like creature that exhibits flocking behavior.
 * It contains methods to update the boid's position and orientation, and to apply flocking rules such as separation, alignment, and cohesion.
 * The class also includes methods to calculate the distance between boids and to reset the boid's state.
 */

import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Boids {
    protected int[] position;
    protected int[] init_position;
    protected int[] init_vitesse;

    protected int[] vitesse;
    protected int[] acceleration;

    protected int[] force;

    protected int orientation;

    protected int init_orientation;

    protected int taille_fen_X;
    protected int taille_fen_Y;

    /**
     * Constructs a new Boids object with the given initial position, velocity, orientation, and window size.
     * @param x The initial x-coordinate of the boid's position
     * @param y The initial y-coordinate of the boid's position
     * @param vx The initial x-component of the boid's velocity
     * @param vy The initial y-component of the boid's velocity
     * @param orientation The initial orientation of the boid
     * @param taille_x The width of the window
     * @param taille_y The height of the window
     */
    public Boids(int x, int y, int vx, int vy, int orientation, int taille_x, int taille_y) {
        position = new int[]{x, y};
        init_position = new int[]{x, y};
        init_vitesse = new int[]{vx, vy};
        init_orientation = orientation;
        vitesse = new int[]{vx, vy};
        acceleration = new int[]{0, 0};
        this.orientation = orientation;
        this.taille_fen_X = taille_x;
        this.taille_fen_Y = taille_y;
    }

    /**
     * Returns the orientation of the boid.
     * @return The orientation of the boid
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * Resets the boid's position, velocity, and orientation to their initial values.
     */
    public void reset() {
        this.position = new int[]{init_position[0], init_position[1]};
        this.vitesse = new int[]{init_vitesse[0], init_vitesse[1]};
        this.orientation = init_orientation;
    }

    /**
     * Sets the orientation of the boid.
     * @param orientation The new orientation of the boid
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    /**
     * Returns the current position of the boid.
     * @return The position of the boid as an array of x and y coordinates
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Returns the current acceleration of the boid.
     * @return The acceleration of the boid as an array of x and y components
     */
    public int[] getAcceleration() {
        return acceleration;
    }

    /**
     * Returns the current velocity of the boid.
     * @return The velocity of the boid as an array of x and y components
     */
    public int[] getVitesse() {
        return vitesse;
    }

    /**
     * Updates the orientation of the boid based on its velocity.
     */
    protected void update_orientation() {
        // je pense beug ici aussi
        orientation = (int) Math.toDegrees(Math.atan2(vitesse[1], vitesse[0]) + Math.PI/2);
        orientation = (orientation < 0) ? (orientation + 360) : orientation;
    }

    /**
     * Updates the position of the boid based on its velocity and window size.
     */
    protected void update_position() {
        // je pense beug ici j'ai essayé avec cos et sin j'ai pas eu de truc concluant
        position[0] += vitesse[0];
        position[1] += vitesse[1];
        position[0] = (position[0] < 0) ? position[0] + taille_fen_X : position[0];
        position[0] = (position[0] > taille_fen_X) ? position[0] - taille_fen_X : position[0];
        position[1] = (position[1] < 0) ? position[1] + taille_fen_Y : position[1];
        position[1] = (position[1] > taille_fen_Y) ? position[1] - taille_fen_Y : position[1];
    }

    /**
     * Updates the velocity of the boid based on a given force.
     * @param vitesse2 The force to be applied to the velocity of the boid
     */
    protected void update_vitesse(int[] vitesse2) {
        // update vitesse
        vitesse[0] = (Math.abs(vitesse[0] + vitesse2[0]) > 30) ? vitesse[0] : (vitesse[0] + vitesse2[0]);
        vitesse[1] = (Math.abs(vitesse[1] + vitesse2[1]) > 30) ? vitesse[1] : (vitesse[1] + vitesse2[1]);

    }

    /**
     * Updates the acceleration of the boid based on a given force.
     * @param force The force to be applied to the acceleration of the boid
     */
    protected void update_acceleration(int[] force) {
        // A BANNIR POUR LE MOMENT NE PAS UTILISER
        // je pense que en tout cas dans un premier temps il ne faut pas passer par les accelerations
        // et acceleration et la force n'ont pas les meme unité on peut pas les ajouter (la masse intervient ici)
        acceleration[0] += force[0];
        acceleration[1] += force[1];
    }
    /**
     * Updates the orientation and position of the boid.
     */
    public void update() {
        // changer l'orientation
        // changer position
        update_orientation();
        update_position();
    }

    /**
     * Calculates the distance between the current boid and another boid.
     * @param other The other boid to calculate the distance to
     * @return The distance between the current boid and the other boid
     */
    public int distance(Boids other) {
        // faudrait aussi regarder les voisions avec modulo
        // enft c'est pas ouf avec modulo
        // int sup_x = Math.max(position[0], other.position[0]);
        // int inf_x = Math.min(position[0], other.position[0]);
        // int sup_y = Math.max(position[1], other.position[1]);
        // int inf_y = Math.min(position[1], other.position[1]);
        // int dx = Math.min(sup_x - inf_x, Math.abs(sup_x - inf_x - taille_fen_X));
        // int dy = Math.min(sup_y - inf_y, Math.abs(sup_y - inf_y - taille_fen_Y));
        int dx = position[0] - other.position[0];
        int dy = position[1] - other.position[1];
        return (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Applies the separation rule to the boid based on its neighbors.
     * @param list_boids An array of boids representing the neighbors of the current boid
     * @param distance_separation The distance threshold for separation
     */
    public void separate(Boids[] list_boids, int distance_separation) {
        // RULE 2 Keeping a small distance between boids
        // ON PEUT CHANGER LES PARAM DE LA REGLE AVEC LA COULEUR
        int[] VectorC = new int[]{0, 0};
        int dx = 0;
        int dy = 0;
        for (Boids boid : list_boids) {
            if (boid.getPosition() != this.position) {
                int dist = distance(boid);
                if (dist < distance_separation) {
                    dx += (position[0] - boid.position[0]) / 14;
                    dy += (position[1] - boid.position[1]) / 14;
                    // on les rajoute
                }
            }
        }
        VectorC[0] = dx;
        VectorC[1] = dy;
        update_vitesse(VectorC);
    }

    /**
     * Applies the alignment rule to the boid based on its neighbors.
     * @param boids An array of boids representing the neighbors of the current boid
     * @param distance_alignement The distance threshold for alignment
     */
    public void align(Boids[] boids, int distance_alignement) {
        // RULE 3 matching nearest neighboors velocity
        // ON PEUT CHANGER LES PARAM DE LA REGLE AVEC LA COULEUR
        int[] VectorPvj = new int[]{0, 0};
        int count = 0;
        for (Boids boid : boids) {
            if (boid.getPosition() != this.position) {
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

    /**
     * Applies the cohesion rule to the boid based on its neighbors.
     * @param boids An array of boids representing the neighbors of the current boid
     * @param distance_essaim The distance threshold for cohesion
     */

    public void cohere(Boids[] boids, int distance_essaim) {
        // RULE 1 go to the center of mass
        // ON PEUT CHANGER LES PARAM DE LA REGLE AVEC LA COULEUR
        int[] VectorPcj = new int[]{0, 0};
        int count = 0;
        for (Boids boid : boids) {
            if (boid.getPosition() != this.position) {
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
    }
}