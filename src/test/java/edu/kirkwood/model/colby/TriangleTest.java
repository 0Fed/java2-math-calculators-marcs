package edu.kirkwood.model.colby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    private Triangle t1;
    private Triangle t2;

    @BeforeEach
    void setUp() {
        t1 = new Triangle();
        t2 = new Triangle(2.00,2.00,2.00,60.00,60.00,60.00);
    }

    // This and all tests involving floating point numbers use the delta value
    // This is to account for rounding errors
    // I learned about this in Bob's Java 1 class and used Google for a refresher on the concept
    @Test
    void getSideA(){
        assertEquals(1.00, t1.getSideA(), 0.01);
        assertEquals(2.00, t2.getSideA(), 0.01);
    }

    @Test
    void testToString(){
        assertEquals("Sides: a=1.00, b=1.00, c=1.00" +
                "\nAngles: A=60.00, B=60.00, C=60.00", t1.toString());
    }

    @Test
    void setSideAPositive(){
        // Act
        t1.setSideA(2.00);
        // Assert
        assertEquals(2.00, t1.getSideA(), 0.01);
    }

    @Test
    void setSideANegative(){
        // Assert and Act
        assertThrows(IllegalArgumentException.class, () -> t1.setSideA(-1.00));
    }

    @Test
    void setSideAZero(){
        // Assert and Act
        assertThrows(IllegalArgumentException.class, () -> t1.setSideA(0));
    }

    @Test
    void getSideB(){
        assertEquals(1.00, t1.getSideB(), 0.01);
        assertEquals(2.00, t2.getSideB(), 0.01);
    }

    @Test
    void setSideBPositive(){
        // Act
        t1.setSideB(2.00);
        // Assert
        assertEquals(2.00, t1.getSideB(), 0.01);
    }

    @Test
    void setSideBNegative(){
        // Assert and Act
        assertThrows(IllegalArgumentException.class, () -> t1.setSideB(-1.00));
    }
    @Test
    void setSideBZero(){
        // Assert and Act
        assertThrows(IllegalArgumentException.class, () -> t1.setSideB(0));
    }

    @Test
    void getSideC(){
        assertEquals(1.00, t1.getSideC(), 0.01);
        assertEquals(2.00, t2.getSideC(), 0.01);
    }

    @Test
    void setSideCPositive(){
        // Act
        t1.setSideC(2.00);
        // Assert
        assertEquals(2.00, t1.getSideC(), 0.01);
    }

    @Test
    void setSideCNegative(){
        // Assert and Act
        assertThrows(IllegalArgumentException.class, () -> t1.setSideC(-1.00));
    }
    @Test
    void setSideCZero(){
        // Assert and Act
        assertThrows(IllegalArgumentException.class, () -> t1.setSideC(0));
    }

    @Test
    void getAngleA(){
        assertEquals(60.00, t1.getAngleA(), 0.01);
        assertEquals(60.00, t2.getAngleA(), 0.01);
    }

    @Test
    void setAngleAAcceptedValue(){
        // Act
        t1.setAngleA(120.00);
        // Assert
        assertEquals(120.00, t1.getAngleA(), 0.01);
    }

    @Test
    void setAngleAAtMax(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleA(180.00));
    }

    @Test
    void setAngleAAboveMax(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleA(200.00));
    }

    @Test
    void setAngleANegative(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleA(-20.00));
    }

    @Test
    void setAngleAZero(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleA(0));
    }

    @Test
    void getAngleB(){
        assertEquals(60.00, t1.getAngleB(), 0.01);
        assertEquals(60.00, t2.getAngleB(), 0.01);
    }

    @Test
    void setAngleBAcceptedValue(){
        // Act
        t1.setAngleB(120.00);
        // Assert
        assertEquals(120.00, t1.getAngleB(), 0.01);
    }

    @Test
    void setAngleBAtMax(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleB(180.00));
    }

    @Test
    void setAngleBAboveMax(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleB(200.00));
    }

    @Test
    void setAngleBNegative(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleB(-20.00));
    }

    @Test
    void setAngleBZero(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleB(0));
    }

    @Test
    void getAngleC(){
        assertEquals(60.00, t1.getAngleC(), 0.01);
        assertEquals(60.00, t2.getAngleC(), 0.01);
    }

    @Test
    void setAngleCAcceptedValue(){
        // Act
        t1.setAngleC(120.00);
        // Assert
        assertEquals(120.00, t1.getAngleC(), 0.01);
    }

    @Test
    void setAngleCAtMax(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleC(180.00));
    }

    @Test
    void setAngleCAboveMax(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleC(200.00));
    }

    @Test
    void setAngleCNegative(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleC(-20.00));
    }

    @Test
    void setAngleCZero(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.setAngleC(0));
    }

    // *** Tests for SSS ***
    @Test
    void solveSSSRightTriangle(){
        // Act
        Triangle result = t1.solveSSS(3.00, 4.00, 5.00);
        // Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(4.00, result.getSideB(), 0.01);
        assertEquals(5.00, result.getSideC(), 0.01);
        assertEquals(36.87, result.getAngleA(), 0.01);
        assertEquals(53.13, result.getAngleB(), 0.01);
        assertEquals(90.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveSSSEquilateral(){
        // Act
        Triangle result = t1.solveSSS(3.00, 3.00, 3.00);
        // Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(3.00, result.getSideB(), 0.01);
        assertEquals(3.00, result.getSideC(), 0.01);
        assertEquals(60.00, result.getAngleA(), 0.01);
        assertEquals(60.00, result.getAngleB(), 0.01);
        assertEquals(60.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveSSSInvalidSides(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.solveSSS(1.00, 2.00, 10.00));
    }

    @Test
    void solveSSSDegenerateTriangle(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.solveSSS(1.00, 2.00, 3.00));
    }

    // *** Tests for SAS ***
    @Test
    void solveSASRightTriangle(){
        // Act
        Triangle result = t1.solveSAS(3.00, 4.00, 90.00);
        //Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(4.00, result.getSideB(), 0.01);
        assertEquals(5.00, result.getSideC(), 0.01);
        assertEquals(36.87, result.getAngleA(), 0.01);
        assertEquals(53.13, result.getAngleB(), 0.01);
        assertEquals(90.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveSASEquilateral(){
        // Act
        Triangle result = t1.solveSAS(3.00, 3.00, 60.00);
        // Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(3.00, result.getSideB(), 0.01);
        assertEquals(3.00, result.getSideC(), 0.01);
        assertEquals(60.00, result.getAngleA(), 0.01);
        assertEquals(60.00, result.getAngleB(), 0.01);
        assertEquals(60.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveSASObtuseAngle(){
        // Act
        Triangle result = t1.solveSAS(3.00, 3.00, 170.00);
        // Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(3.00, result.getSideB(), 0.01);
        assertEquals(5.97, result.getSideC(), 0.01);
        assertEquals(5.00, result.getAngleA(), 0.01);
        assertEquals(5.00, result.getAngleB(), 0.01);
        assertEquals(170.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveSASInvalidAngleSum(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.solveSAS(1.00, 2.00, 180.00));
    }

    // *** Tests for SSA ***
    @Test
    void solveSSATwoRightTriangle(){
        // Act
        List<Triangle> result = t1.solveSSA(3.00, 4.00, 36.87);
        //Assert
        // I had help from Gemini to figure out how to work with a list of Triangles
        // To account for the different SSA cases
        // Check the size of the list first
        assertEquals(2, result.size());
        // getFirst() in this case represents the first index in the list
        Triangle triangle1 = result.getFirst();
        Triangle triangle2 = result.get(1);
        // Check the sides and angles of the triangle
        assertEquals(3.00, triangle1.getSideA(), 0.01);
        assertEquals(4.00, triangle1.getSideB(), 0.01);
        assertEquals(5.00, triangle1.getSideC(), 0.01);
        assertEquals(36.87, triangle1.getAngleA(), 0.01);
        assertEquals(53.13, triangle1.getAngleB(), 0.01);
        assertEquals(90.00, triangle1.getAngleC(), 0.01);
        // Check values of second triangle
        assertEquals(3.00, triangle2.getSideA(), 0.01);
        assertEquals(4.00, triangle2.getSideB(), 0.01);
        assertEquals(1.40, triangle2.getSideC(), 0.01);
        assertEquals(36.87, triangle2.getAngleA(), 0.01);
        assertEquals(126.87, triangle2.getAngleB(), 0.01);
        assertEquals(16.26, triangle2.getAngleC(), 0.01);

    }

    @Test
    void solveSSAOneTriangle(){
        // Act
        List<Triangle> result = t1.solveSSA(2.50, 5.00, 30.00);
        // Assert
        // Check size of the list
        assertEquals(1, result.size());
        Triangle triangle = result.getFirst();
        assertEquals(2.50, triangle.getSideA(), 0.01);
        assertEquals(5.00, triangle.getSideB(), 0.01);
        assertEquals(4.33, triangle.getSideC(), 0.01);
        assertEquals(30.00, triangle.getAngleA(), 0.01);
        assertEquals(90.00, triangle.getAngleB(), 0.01);
        assertEquals(60.00, triangle.getAngleC(), 0.01);
    }

    @Test
    void solveSSAOneTriangleEquilateral(){
        // Act
        List<Triangle> result = t1.solveSSA(3.00, 3.00, 60.00);
        // Assert
        // Check size of the list
        assertEquals(1, result.size());
        Triangle triangle = result.getFirst();
        assertEquals(3.00, triangle.getSideA(), 0.01);
        assertEquals(3.00, triangle.getSideB(), 0.01);
        assertEquals(3.00, triangle.getSideC(), 0.01);
        assertEquals(60.00, triangle.getAngleA(), 0.01);
        assertEquals(60.00, triangle.getAngleB(), 0.01);
        assertEquals(60.00, triangle.getAngleC(), 0.01);
    }

    @Test
    void solveSSAZeroTriangles(){
        // Act
        List<Triangle> result = t1.solveSSA(2.00, 10.00, 30.00);
        // Assert
        // This checks that there are zero Triangle objects in the list of Triangles
        assertEquals(0, result.size());
    }

    @Test
    void solveSSATwoTriangles(){
        // Act
        List<Triangle> result = t1.solveSSA(7.00, 10.00, 30.00);

        // Assert
        // Check the size of the list is 2
        assertEquals(2, result.size());
        // Assign the two triangles in the list to variables
        Triangle triangle1 = result.getFirst();
        Triangle triangle2 = result.get(1);
        // Check values of triangle 1
        assertEquals(7.00, triangle1.getSideA(), 0.01);
        assertEquals(10.00, triangle1.getSideB(), 0.01);
        assertEquals(13.56, triangle1.getSideC(), 0.01);
        assertEquals(30.00, triangle1.getAngleA(), 0.01);
        assertEquals(45.59, triangle1.getAngleB(), 0.01);
        assertEquals(104.42, triangle1.getAngleC(), 0.01);
        // Check values of triangle 2
        assertEquals(7.00, triangle2.getSideA(), 0.01);
        assertEquals(10.00, triangle2.getSideB(), 0.01);
        assertEquals(3.76, triangle2.getSideC(), 0.01);
        assertEquals(30.00, triangle2.getAngleA(), 0.01);
        assertEquals(134.42, triangle2.getAngleB(), 0.01);
        assertEquals(15.59, triangle2.getAngleC(), 0.01);
    }

    // *** Tests for ASA ***
    @Test
    void solveASARightTriangle(){
        // Act
        Triangle result = t1.solveASA(36.87, 5.00, 53.13);
        //Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(4.00, result.getSideB(), 0.01);
        assertEquals(5.00, result.getSideC(), 0.01);
        assertEquals(36.87, result.getAngleA(), 0.01);
        assertEquals(53.13, result.getAngleB(), 0.01);
        assertEquals(90.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveASAEquilateral(){
        // Act
        Triangle result = t1.solveASA(60.00, 3.00, 60.00);
        // Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(3.00, result.getSideB(), 0.01);
        assertEquals(3.00, result.getSideC(), 0.01);
        assertEquals(60.00, result.getAngleA(), 0.01);
        assertEquals(60.00, result.getAngleB(), 0.01);
        assertEquals(60.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveASAInvalidAngleSum(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.solveASA(120.00, 2.00, 70.00));
    }

    // *** Tests for AAS ***
    @Test
    void solveAASRightTriangle(){
        // Act
        Triangle result = t1.solveAAS(36.87, 53.13, 3.00);
        //Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(4.00, result.getSideB(), 0.01);
        assertEquals(5.00, result.getSideC(), 0.01);
        assertEquals(36.87, result.getAngleA(), 0.01);
        assertEquals(53.13, result.getAngleB(), 0.01);
        assertEquals(90.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveAASEquilateral(){
        // Act
        Triangle result = t1.solveAAS(60.00, 60.00, 3.00);
        // Assert
        assertEquals(3.00, result.getSideA(), 0.01);
        assertEquals(3.00, result.getSideB(), 0.01);
        assertEquals(3.00, result.getSideC(), 0.01);
        assertEquals(60.00, result.getAngleA(), 0.01);
        assertEquals(60.00, result.getAngleB(), 0.01);
        assertEquals(60.00, result.getAngleC(), 0.01);
    }

    @Test
    void solveAASInvalidAngleSum(){
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> t1.solveAAS(90.00, 90.00, 10.00));
    }

    @Test
    void isValidTriangleTrue(){
        // Act and Assert
        assertTrue(t1.isValidTriangle(3.00, 4.00, 5.00));
    }

    @Test
    void isValidTriangleFalse(){
        // Act and Assert
        assertFalse(t1.isValidTriangle(3.00, 4.00, 10.00));
    }

    @Test
    void isValidTriangleFalseBoundary(){
        // Act and Assert
        assertFalse(t1.isValidTriangle(3.00, 4.00, 7.00));
    }

    @Test
    void calculatePerimeter(){
        // Arrange
        t1 = new Triangle(3.00, 4.00, 5.00, 36.87, 53.13, 90.00);
        // Act and Assert
        double expected = 12.0;
        assertEquals(expected, t1.calculatePerimeter(), 0.01);
    }

    @Test
    void calculatePerimeterWithDecimals(){
        // Arrange
        t1 = new Triangle(3.50, 4.25, 5.75, 30.00, 45.00, 105.00);
        // Act and Assert
        double expected = 13.5;
        assertEquals(expected, t1.calculatePerimeter(), 0.01);
    }

    @Test
    void calculateAreaRightTriangle(){
        // Arrange
        t1 = new Triangle(3.00, 4.00, 5.00, 36.87, 53.13, 90.00);
        // Act and Assert
        double expected = 6.0;
        assertEquals(expected, t1.calculateArea(),0.01);
    }

    @Test
    void calculateAreaHeronsFormula(){
        // Arrange
        t1 = new Triangle(2.00, 2.00, 2.00, 60.00, 60.00, 60.00);
        // Act and Assert
        double expected = 1.73;
        assertEquals(expected, t1.calculateArea(),0.01);
    }

    @Test
    void equals(){
        // Act
        t1 = new Triangle(2.00, 2.00, 2.00, 60.00, 60.00, 60.00);
        // Assert
        assertTrue(t1.equals(t2));
    }

    @Test
    void notEquals(){
        // Act
        t1 = new Triangle(1.00, 2.00, 2.00, 60.00, 60.00, 60.00);
        // Assert
        assertTrue(!t1.equals(t2));
    }

    @Test
    void compareToEquals(){
        // Arrange
        t1 = new Triangle(3.00, 4.00, 5.00, 36.87, 53.13, 90.00);
        t2 = new Triangle(3.00, 4.00, 5.00, 36.87, 53.13, 90.00);
        // Act and Assert
        assertEquals(0, t1.compareTo(t2), 0.01, "6 == 6");
    }

    @Test
    void compareToGreaterThan(){
        // Arrange
        t1 = new Triangle(6.00, 8.00, 10.00, 36.87, 53.13, 90.00);
        t2 = new Triangle(3.00, 4.00, 5.00, 36.87, 53.13, 90.00);
        // Act and Assert
        assertTrue(t1.compareTo(t2) > 0, "12 > 6");
    }

    @Test
    void compareToLessThan(){
        t1 = new Triangle(3.00, 4.00, 5.00, 36.87, 53.13, 90.00);
        t2 = new Triangle(6.00, 8.00, 10.00, 36.87, 53.13, 90.00);
        // Act and Assert
        assertTrue(t1.compareTo(t2) < 0, "6 < 12");
    }

}