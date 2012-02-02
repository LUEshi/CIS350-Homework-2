/**
 * 
 */
package edu.upenn.cis350.gpx;


import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;

/**
 * @author appsam
 *
 */
public class GPXcalculatorTest extends TestCase {

	ArrayList<GPXtrkseg> trackSegs;
	ArrayList<GPXtrkpt> points1;
	ArrayList<GPXtrkpt> points2;
	GPXtrkseg seg1;
	GPXtrkseg seg2;
	GPXtrk track;
	final double DELTA = .0001;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		trackSegs = new ArrayList<GPXtrkseg>();
		points1 = new ArrayList<GPXtrkpt>();
		points1.add(new GPXtrkpt(0,0,new Date()));
		points1.add(new GPXtrkpt(3,0,new Date()));
		points1.add(new GPXtrkpt(3,4,new Date()));
		points2 = new ArrayList<GPXtrkpt>();
		seg1 = new GPXtrkseg(points1);
		seg2 = new GPXtrkseg(points2);
		track = new GPXtrk("test",trackSegs);
	}
	
	//if a track is null, distance should be -1
	public void testNullTrack(){
		assertEquals(-1,GPXcalculator.calculateDistanceTraveled(null),DELTA);
	}
	
	//empty track should return -1
	public void testEmptyTrack(){
		assertEquals(-1,GPXcalculator.calculateDistanceTraveled(track),DELTA);
	}
	

	//testing normal functionality with 1 segment
	public void testSingleSegment()
	{
		trackSegs.add(seg1);
		//3,4,5 triangle
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);
	}
	
	//any null track segments should have a distance 0;
	public void testNullSeg(){
		trackSegs.add(null);
		trackSegs.add(null);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);
	}
	
	public void testEmptySegment()
	{
		trackSegs.add(seg2);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);
	}
	
	public void testSinglePointSegment()
	{
		points2.add(new GPXtrkpt(3,4,new Date()));
		trackSegs.add(seg2);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);
	}
	
	public void testNullPointInSegment()
	{
		trackSegs.add(seg1);
		points1.add(null);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);
	}
	public void testMultipleSegments()
	{
		trackSegs.add(seg2);
		trackSegs.add(seg1);
		trackSegs.add(null);
		trackSegs.add(seg1);
		assertEquals(10,GPXcalculator.calculateDistanceTraveled(track),DELTA);
	}

	public void testLatituteOutOfBounds1()
	{
		//boundary condition
		points1.add(new GPXtrkpt(-90,4,new Date()));
		trackSegs.add(seg1);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		points1.remove(points1.size()-1);
		
		//normal behavior expected
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		
	}
	public void testLatituteOutOfBounds2()
	{
		points1.add(new GPXtrkpt(-100,4,new Date()));
		trackSegs.add(seg1);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		points1.remove(points1.size()-1);
		
		//normal behavior expected
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		
	}
	public void testLatituteOutOfBounds3()
	{
		//boundary condition
		points1.add(new GPXtrkpt(90,4,new Date()));
		trackSegs.add(seg1);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		points1.remove(points1.size()-1);
				
		//normal behavior expected
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		
	}
	public void testLatituteOutOfBounds4()
	{
	
		points1.add(new GPXtrkpt(100,4,new Date()));
		trackSegs.add(seg1);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		points1.remove(points1.size()-1);
		
		//normal behavior expected
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		
	}


	public void testLongituteOutOfBounds1()
	{
		
		//boundary condition
		points1.add(new GPXtrkpt(0,-180,new Date()));
		trackSegs.add(seg1);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		points1.remove(points1.size()-1);
				
		//normal behavior expected
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		
	}
	public void testLongituteOutOfBounds2()
	{
		
		points1.add(new GPXtrkpt(0,-190,new Date()));
		trackSegs.add(seg1);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		points1.remove(points1.size()-1);
		
		//normal behavior expected
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		
	}
	public void testLongituteOutOfBounds3()
	{
		//boundary condition
		points1.add(new GPXtrkpt(0,180,new Date()));
		trackSegs.add(seg1);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		points1.remove(points1.size()-1);
				
		//normal behavior expected
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		
	}
	public void testLongituteOutOfBounds4()
	{
		
		points1.add(new GPXtrkpt(0,190,new Date()));
		trackSegs.add(seg1);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		points1.remove(points1.size()-1);
				
		//normal behavior expected
		assertEquals(5,GPXcalculator.calculateDistanceTraveled(track),DELTA);	
		
	}


	
}
