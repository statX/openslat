package org.openslat.model.edp;

import java.util.ArrayList;
import org.openslat.interfaces.DifferentiableFunction;
import org.openslat.interfaces.DistributionFunction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * EDPIM represents the seismic demand - intensity measure relationship where
 * seismic demand is measured by a known engineering demand parameter such as
 * interstory drift or floor acceleration.
 * 
 * EDPIM can be constructed with a discrete function or a combined parametric
 * function as it returns a distribution instead of a single value.
 * 
 * @author Alan Williams
 */
@JsonSerialize
public class EDPIM {

	private String name;
	private Double epistemicWeight = 1.0;
	private DistributionFunction distributionFunction;

	/**
	 * Gets the continuous distributionFunction.
	 * 
	 * @return distribution distributionFunction
	 */
	public DistributionFunction getDistributionFunction() {
		return this.distributionFunction;
	}

	public void setDistributionFunction(DistributionFunction model) {
		this.distributionFunction = model;
	}

	public void constructDistributionFunctionFromModels(
			DifferentiableFunction meanModel, DifferentiableFunction stddModel) {
		this.distributionFunction = new EDPIMParametricModel(meanModel,
				stddModel);
	}

	public void constructDistributionFunctionDiscreteType1Table(
			ArrayList<ArrayList<Double>> table) {
		this.distributionFunction = new EDPIMDiscreteModel();
		((EDPIMDiscreteModel) distributionFunction).typeOneTableInput(table);
	}

	public void constructDistributionFunctionDiscreteType2Table(
			ArrayList<ArrayList<Double>> table) {
		this.distributionFunction = new EDPIMDiscreteModel();
		((EDPIMDiscreteModel) distributionFunction).typeTwoTableInput(table);
	}

	public void constructDistributionFunctionParametricType1Table(
			ArrayList<ArrayList<Double>> table) {
		this.distributionFunction = new EDPIMParametricModel();
		((EDPIMParametricModel) distributionFunction).typeOneTableInput(table);
	}

	public void constructDistributionFunctionParametricType2Table(
			ArrayList<ArrayList<Double>> table) {
		this.distributionFunction = new EDPIMParametricModel();
		((EDPIMParametricModel) distributionFunction).typeTwoTableInput(table);
	}

	public Double getEpistemicWeight() {
		return epistemicWeight;
	}

	public void setEpistemicWeight(Double epistemicWeight) {
		this.epistemicWeight = epistemicWeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
