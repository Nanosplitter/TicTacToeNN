package ttt_ai;

import java.io.Serializable;

public class Connection implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4148923509399945603L;
	private Neuron from;     // Connection goes from. . .
    private Neuron to;       // To. . .
    private float weight;   // Weight of the connection. . .

    // Constructor  builds a connection with a random weight
    public Connection(Neuron a_, Neuron b_) {
        from = a_;
        to = b_;
        weight = (float) Math.random()*2-1;
    }
    
    // In case I want to set the weights manually, using this for testing
    public Connection(Neuron a_, Neuron b_, float w) {
        from = a_;
        to = b_;
        weight = w;
    }

    public Neuron getFrom() {
        return from;
    }
    
    public Neuron getTo() {
        return to;
    }  
    
    public float getWeight() {
        return weight;
    }
    
    public void setWight(float w) {
    	weight = w;
    }

    // Changing the weight of the connection
    public void adjustWeight(float deltaWeight) {
        weight += deltaWeight;
    }


}
