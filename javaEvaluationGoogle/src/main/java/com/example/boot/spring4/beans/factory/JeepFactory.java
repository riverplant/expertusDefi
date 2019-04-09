package com.example.boot.spring4.beans.factory;

import com.example.boot.spring4.beans.Jeep;
/**
 * 
 * @author riverplant
 *
 */
public class JeepFactory {

	public Jeep createJeep() {
		return new Jeep();
	}
}
