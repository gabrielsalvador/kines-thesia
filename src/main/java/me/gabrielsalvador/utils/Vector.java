package me.gabrielsalvador.utils;
import processing.core.PVector;


/* just a wrapper for the processing class PVector */
public class Vector {
        private PVector pVector;
    
        public Vector() {
            this.pVector = new PVector();
        }
    
        public Vector(float x, float y) {
            this.pVector = new PVector(x, y);
        }
    
        public Vector(float x, float y, float z) {
            this.pVector = new PVector(x, y, z);
        }
        
        public PVector getPVector() {
            return this.pVector;
        }
        
        public boolean equals(Vector v) {
            return this.pVector.equals(v.getPVector());
        }
        
        public float getX() {
            return this.pVector.x;
        }

        public float getY() {
            return this.pVector.y;
        }
}
    