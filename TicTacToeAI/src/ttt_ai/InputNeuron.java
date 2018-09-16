package ttt_ai;

public class InputNeuron extends Neuron {
    public InputNeuron() {
        super();
    }
    
    public InputNeuron(int i) {
        super(i);
    }

    public void input(float d) {
        output = d;
    }

}