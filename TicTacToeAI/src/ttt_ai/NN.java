package ttt_ai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NN implements Comparable<NN>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4805268476314170826L;
	InputNeuron inputs[];
	HiddenNeuron hidden[][];
	OutputNeuron outputs[];
	ArrayList<Neuron> neurons = new ArrayList<Neuron>();
	Random rnd = new Random();
	ArrayList<Integer> turnCounts = new ArrayList<Integer>();
	float turnAverage;
	float winCount = 0;
	int tempCount = 0;

	// For creating first generation
	public NN(int numInputs, int numOutputs, int numHiddenLayers, int widthHiddenLayers) {
		inputs = new InputNeuron[numInputs + 1];
		hidden = new HiddenNeuron[numHiddenLayers][widthHiddenLayers + 1];
		outputs = new OutputNeuron[numOutputs];
		// Make input neurons
		for (int i = 0; i < inputs.length - 1; i++) {
			inputs[i] = new InputNeuron();
			neurons.add(inputs[i]);
		}

		// Make hidden neurons
		for (int i = 0; i < hidden.length; i++) {
			for (int j = 0; j < hidden[1].length - 1; j++) {
				hidden[i][j] = new HiddenNeuron();
				neurons.add(hidden[i][j]);
			}
		}
		
		// Make output neurons
		for (int i = 0; i < outputs.length; i++) {
			outputs[i] = new OutputNeuron();
			neurons.add(outputs[i]);
		}

		// Make bias neurons
		inputs[inputs.length - 1] = new InputNeuron(1);
		for (int i = 0; i < hidden.length; i++) {
			hidden[i][hidden[i].length - 1] = new HiddenNeuron(1);
			neurons.add(hidden[i][hidden.length - 1]);
		}
		
		//Make bias connections for first layer
		for (int inpt = 0; inpt < inputs.length; inpt++) {
			for (int h = 0; h < hidden[0].length - 1; h++) {
				Connection c = new Connection(inputs[inputs.length - 1], hidden[0][h]);
				inputs[inputs.length - 1].addConnection(c);
				hidden[0][h].addConnection(c);
			}
		}
		
		//Make bias connections for hidden layers
		for (int lyr = 1; lyr < hidden.length; lyr++) {
			for (int h = 0; h < hidden[0].length - 1; h++) {
				Connection c = new Connection(hidden[lyr][hidden[lyr].length - 1], hidden[lyr][h]);
				//System.out.println(hidden[lyr][hidden[lyr].length - 1]);
				hidden[lyr][hidden[lyr].length - 1].addConnection(c);
				hidden[lyr][h].addConnection(c);
			}
		}
		
		//Make bias connections for output layer
		for (int h = 0; h < hidden[1].length; h++) {
			for (int out = 0; out < outputs.length; out++) {
				Connection c = new Connection(hidden[hidden.length - 1][hidden[hidden.length - 1].length - 1], outputs[out]);
				hidden[hidden.length - 1][hidden[hidden.length - 1].length - 1].addConnection(c);
				outputs[out].addConnection(c);
			}
		}

		

		// Make input connections to the first layer
		for (int inpt = 0; inpt < inputs.length - 1; inpt++) {
			for (int h = 0; h < hidden[0].length - 1; h++) {
				Connection c = new Connection(inputs[inpt], hidden[0][h]);
				inputs[inpt].addConnection(c);
				hidden[0][h].addConnection(c);
				
				
			}
		}

		// Make connections between hidden layers
		for (int lyr = 1; lyr < hidden.length - 1; lyr++) {
			for (int h = 0; h < hidden[1].length - 1; h++) {
				for (int h1 = 0; h1 < hidden[1].length - 1; h1++) {
					Connection c = new Connection(hidden[lyr][h], hidden[lyr + 1][h1]);
					hidden[lyr][h].addConnection(c);
					hidden[lyr + 1][h1].addConnection(c);
				}
			}
		}

		// Connect the hidden layers to the output neurons
		for (int h = 0; h < hidden[1].length - 1; h++) {
			for (int out = 0; out < outputs.length; out++) {
				Connection c = new Connection(hidden[hidden.length - 1][h], outputs[out]);
				hidden[hidden.length - 1][h].addConnection(c);
				//System.out.println(outputs[out]);
				outputs[out].addConnection(c);
			}
		}
		
	}
	/*
	 * // For creating generations after the first, negative changeChance means //
	 * decrease weight public NN(int numInputs, int numOutputs, int numHiddenLayers,
	 * int widthHiddenLayers, int changeChance) { InputNeuron inputs[] = new
	 * InputNeuron[numInputs + 1]; HiddenNeuron hidden[][] = new
	 * HiddenNeuron[numHiddenLayers][widthHiddenLayers + 1]; OutputNeuron outputs[]
	 * = new OutputNeuron[numOutputs];
	 * 
	 * // Make input neurons for (int i = 0; i < inputs.length - 1; i++) { inputs[i]
	 * = new InputNeuron(); }
	 * 
	 * // Make hidden neurons for (int i = 0; i < hidden.length; i++) { for (int j =
	 * 0; j < hidden[1].length - 1; j++) { hidden[i][j] = new HiddenNeuron(); } }
	 * 
	 * // Make bias neurons inputs[inputs.length - 1] = new InputNeuron(1); for (int
	 * i = 0; i < hidden.length; i++) { hidden[i][hidden.length - 1] = new
	 * HiddenNeuron(1); }
	 * 
	 * // Make output neurons for (int i = 0; i < outputs.length; i++) { outputs[i]
	 * = new OutputNeuron(); }
	 * 
	 * // Make input connections to the first layer for (int inpt = 0; inpt <
	 * inputs.length; inpt++) { for (int h = 0; h < hidden[1].length; h++) {
	 * Connection c; if (rnd.nextInt(100) < changeChance) { if (changeChance < 0) {
	 * c = new Connection(inputs[inpt], hidden[0][h], (float) rnd.nextDouble()); }
	 * else { c = new Connection(inputs[inpt], hidden[0][h], (float)
	 * rnd.nextDouble()); } c = new Connection(inputs[inpt], hidden[0][h]); } else {
	 * c = new Connection(inputs[inpt], hidden[0][h]); }
	 * 
	 * inputs[inpt].addConnection(c); hidden[0][h].addConnection(c); } }
	 * 
	 * // Make connections between hidden layers for (int lyr = 1; lyr <
	 * hidden.length - 1; lyr++) { for (int h = 0; h < hidden[1].length; h++) { for
	 * (int h1 = 0; h1 < hidden[1].length; h1++) {
	 * 
	 * Connection c; if (rnd.nextInt(100) < changeChance) { if (changeChance < 0) {
	 * c = new Connection(hidden[lyr][h], hidden[lyr + 1][h1], (float)
	 * rnd.nextDouble() * (-1)); } else { c = new Connection(hidden[lyr][h],
	 * hidden[lyr + 1][h1], (float) rnd.nextDouble()); } c = new
	 * Connection(hidden[lyr][h], hidden[lyr + 1][h1]); } else { c = new
	 * Connection(hidden[lyr][h], hidden[lyr + 1][h1]); }
	 * 
	 * hidden[lyr][h].addConnection(c); hidden[lyr + 1][h1].addConnection(c); } } }
	 * 
	 * // Connect the hidden layers to the output neurons for (int h = 0; h <
	 * hidden[1].length; h++) { for (int out = 0; out < outputs.length; out++) {
	 * 
	 * Connection c; if (rnd.nextInt(100) < changeChance) { if (changeChance < 0) {
	 * c = new Connection(hidden[hidden.length][h], outputs[out], (float)
	 * rnd.nextDouble() * (-1)); } else { c = new
	 * Connection(hidden[hidden.length][h], outputs[out], (float) rnd.nextDouble());
	 * } c = new Connection(hidden[hidden.length][h], outputs[out]); } else { c =
	 * new Connection(hidden[hidden.length][h], outputs[out]); }
	 * 
	 * hidden[hidden.length][h].addConnection(c); outputs[out].addConnection(c); } }
	 * }
	 */

	public void adjustWeights(int changeChance) {
		for (Neuron n : neurons) {
			for (Connection c : n.getConnections()) {
				if (rnd.nextInt(100) < changeChance) {
					if (rnd.nextInt(2) == 0) {
//						System.out.println("Before: " + c.getWeight());
						c.adjustWeight((float) rnd.nextDouble() * (rnd.nextInt(12) + 1));
//						System.out.println("After: " + c.getWeight());
					} else {
//						System.out.println("Before decrease: " + c.getWeight());
						c.adjustWeight((float) rnd.nextDouble() * (float) -1 * (rnd.nextInt(12) + 1));
//						System.out.println("After decrease: " + c.getWeight());
					}
				}
			}
		}
	}

	public int makeMove(float[] board) {
		ArrayList<Float> newBoard = new ArrayList<Float>();
		for (Float f : board) {
			if (f == 1) {
				newBoard.add((float) 1);
			} else {
				newBoard.add((float) 0);
			}
			
			if (f == -1) {
				newBoard.add((float) 1);
			} else {
				newBoard.add((float) 0);
			}
		}
		float[] newBoardFloat = new float[newBoard.size()];
		for (int i = 0; i < newBoard.size(); i++) {
			newBoardFloat[i] = newBoard.get(i);
		}
		ArrayList<Float> out = feedForward(newBoardFloat);
		
//		for(Float f : board) {
//			System.out.print(f + ", ");
//		}
		//System.out.println();
//		for(Float f : out) {
//			System.out.print(f + ", ");
//		}
		//System.out.println(out);
		while (board[out.indexOf(Collections.max(out))] != 0) {
			//System.out.println(board[out.indexOf(Collections.max(out))]);
			out.set(out.indexOf(Collections.max(out)), (float)-1);
			//System.out.println("Value: " + Collections.max(out) + " Board Val: " + board[out.indexOf(Collections.max(out))]);
			//System.out.println("Size: " + out.size());
		}
		//System.out.println(out.indexOf(Collections.max(out)));
		return out.indexOf(Collections.max(out));
	}

	public ArrayList<Float> feedForward(float[] inputVals) {
		ArrayList<Float> returnOutputs = new ArrayList<Float>();

		// Feed the input with an array of inputs
		for (int i = 0; i < inputVals.length; i++) {
			inputs[i].input(inputVals[i]);
		}

		// Have the hidden layer calculate its output
		for (int i = 0; i < hidden.length; i++) {
			for (int j = 0; j < hidden[1].length - 1; j++) {
				hidden[i][j].calcOutput();
			}
		}

		for (int i = 0; i < outputs.length; i++) {
			outputs[i].calcOutput();
			returnOutputs.add(outputs[i].getOutput());
		}

		return returnOutputs;
	}

	public void reportResult(int turns, int won) {
		turnCounts.add(turns);
		tempCount = 0;
		for (Integer i : turnCounts) {
			tempCount += i;
		}
		turnAverage = tempCount / turnCounts.size();

		winCount += won;
		
	}

	public int compareTo(NN n) {
		if (winCount > n.winCount) {
			return -1;
		} else if (winCount == n.winCount) {
			if (turnAverage > n.turnAverage) {
				return -1;
			} else if (turnAverage < n.turnAverage) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 1;
		}

	}

	public static void main(String[] args) {
		NN nn = new NN(9, 9, 3, 5);
		float[] board = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		System.out.println(nn.makeMove(board));
//		for (int i = 0; i < 1000; i++) {
		nn.adjustWeights(20);
//		}
		System.out.println(nn.makeMove(board));
		
	}
}
