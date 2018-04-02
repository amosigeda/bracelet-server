package com.bracelet;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.bracelet.util.Utils;

public class Point {
	public static void main(String[] args) {
		 Point2D.Double point = new Point2D.Double(116.410, 39.913);
	      
		    List<Point2D.Double> pts = new ArrayList<Point2D.Double>();  
		    pts.add(new Point2D.Double(116.395, 39.910));  //1
		    pts.add(new Point2D.Double(116.394, 39.914));  //2
		    pts.add(new Point2D.Double(116.403, 39.920));  //3
		    pts.add(new Point2D.Double(116.402, 39.914));  //4
		    pts.add(new Point2D.Double(116.410, 39.913));  //5
		    
		    if(Utils.IsPtInPoly(point, pts)){  
		        System.out.println("点在多边形内");  //2
		    }else{  
		        System.out.println("点在多边形外");  //1
		    }  
	}

}
