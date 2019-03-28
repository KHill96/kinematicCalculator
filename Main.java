import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.*;
import javax.imageio.ImageIO;

/**
 * This program will calculate missing variables of the 4 kinematic equations. At least 3 variables need to be input
 * by the user
 *
 * @author Kenneth Hill
 * @version 1.0
 * @since 5/27/2019
 */


/**
 * The class Kinematic GUI will create the interface and the action listener for the button will calculate according to the user's input
 */
class KinematicGUI {

    KinematicGUI() throws IOException {

        // I created an array to hold the text fields to check if enough information was entered
        JTextField [] textFields = new JTextField[5];

        // Setting up the initial frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Kinematic Equations and Projectile Motion Calculator");
        frame.setBounds(100, 100, 580, 261);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        // Source for Image: https://www.quora.com/What-are-all-the-possible-forms-of-the-kinematic-equations-of-motion
        BufferedImage kinematicImage = ImageIO.read(new File("kinematics.png"));
        JLabel lblImage = new JLabel(new ImageIcon(kinematicImage));
        lblImage.setBounds(171, 108, 327, 90);
        frame.getContentPane().add(lblImage);

        // Top leftmost label to give instructions for user
        JLabel lbl3Variables = new JLabel("Input at least 3 Variables");
        lbl3Variables.setBounds(12, 14, 196, 15);
        frame.getContentPane().add(lbl3Variables);

        // Label describing the equations and dimensions used
        JLabel lblKinematic = new JLabel("Kinematic Equations in One Dimension(X or Y)");
        lblKinematic.setBounds(227, 12, 345, 19);
        frame.getContentPane().add(lblKinematic);

        // Initial velocity
        JTextField txtInitialVelocity = new JTextField();
        txtInitialVelocity.setBounds(12, 67, 61, 19);
        frame.getContentPane().add(txtInitialVelocity);
        textFields[0] = txtInitialVelocity;
        JLabel lblInitialVelocity = new JLabel("V0");
        lblInitialVelocity.setBounds(12, 52, 24, 15);
        frame.getContentPane().add(lblInitialVelocity);
        JLabel lbl_Mps = new JLabel("m/s");
        lbl_Mps.setBounds(76, 69, 24, 15);
        frame.getContentPane().add(lbl_Mps);

        // "Final" Velocity
        JTextField txtVelocity = new JTextField();
        txtVelocity.setBounds(121, 67, 61, 19);
        frame.getContentPane().add(txtVelocity);
        textFields[1] = txtVelocity;
        JLabel lblVelocity = new JLabel("Velocity");
        lblVelocity.setBounds(121, 52, 61, 15);
        frame.getContentPane().add(lblVelocity);
        JLabel lbl_Mps_1 = new JLabel("m/s");
        lbl_Mps_1.setBounds(184, 69, 24, 15);
        frame.getContentPane().add(lbl_Mps_1);

        // Distance
        JTextField txtDistance = new JTextField();
        txtDistance.setBounds(226, 67, 61, 19);
        frame.getContentPane().add(txtDistance);
        textFields[2] = txtDistance;
        JLabel lblDistance = new JLabel("Distance");
        lblDistance.setBounds(226, 52, 70, 15);
        frame.getContentPane().add(lblDistance);
        JLabel lblM_Distance = new JLabel("m");
        lblM_Distance.setBounds(288, 69, 24, 15);
        frame.getContentPane().add(lblM_Distance);

        // Acceleration
        JTextField txtAcceleration = new JTextField();
        txtAcceleration.setBounds(320, 67, 61, 19);
        frame.getContentPane().add(txtAcceleration);
        textFields[3] = txtAcceleration;
        JLabel lblAcceleration = new JLabel("Acceleration");
        lblAcceleration.setBounds(320, 52, 88, 15);
        frame.getContentPane().add(lblAcceleration);
        JLabel lblMps2_Acceleration = new JLabel("m/s^2");
        lblMps2_Acceleration.setBounds(381, 69, 42, 15);
        frame.getContentPane().add(lblMps2_Acceleration);

        // Time
        JTextField txtTime = new JTextField();
        txtTime.setBounds(450, 67, 50, 19);
        frame.getContentPane().add(txtTime);
        textFields [4] = txtTime;
        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(450, 52, 50, 15);
        frame.getContentPane().add(lblTime);
        JLabel lblS_Time = new JLabel("s");
        lblS_Time.setBounds(502, 68, 20, 17);
        frame.getContentPane().add(lblS_Time);

        // Button to calculate missing variables
        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(26, 130, 117, 25);
        frame.getContentPane().add(btnCalculate);

        // Action Listener to calculate missing variables
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                // Variables
                DecimalFormat twoPlaces = new DecimalFormat(".##");


                double initialVelocity = 0.00d; // Initial Velocity Variable
                double velocity = 0.00d; // Velocity Variable
                double distance = 0.00d; // Distance Variable
                double acceleration = 0.00d; // Acceleration Variable
                double time = 0.00d; // Time variable
                int count = 0; // Counter used to see how many input fields are empty

                // Loop to check how many input fields are empty
                for (JTextField txtField: textFields) {
                    if (txtField.getText().isEmpty())
                        count++;
                }


                switch (count){
                    // All fields have some value in them
                    case 0:{
                        try {
                            // Initialize the variables to their respective fields, if theres a formatting issue, the catch block will handle it
                            // Each field is rounded to two decimal points
                            initialVelocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtInitialVelocity.getText())));
                            velocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtVelocity.getText())));
                            distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                            acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                            time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));

                            // If distance or time are negative throw an exception
                            if (time < 0.0 || distance < 0.0)
                                throw new IllegalArgumentException();


                            else
                                JOptionPane.showMessageDialog(frame, "All values already input");

                        }
                        // I use a general exception here because I'm really outputting the same thing for each exception, no point in multiple catch blocks doing the same thing
                        catch (ParseException pe){
                            JOptionPane.showMessageDialog(frame, "Error: Invalid input, try again");
                        }

                        break;
                    }
                    // 1 variable missing
                    case 1:{
                        // Cases for all possible empty fields
                        try {
                            // Initial velocity is missing
                            if (txtInitialVelocity.getText().isEmpty()){
                                velocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtVelocity.getText())));
                                distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                                acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                                time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));
                                // Can't have negative time or distance
                                if (time < 0.0 || distance < 0.0) throw new IllegalArgumentException();
                                // If either time or distance is 0 and the other isn't, there's an error
                                if ((time == 0.0 && distance != 0.0) || (time != 0.0 && distance == 0.0)) throw new IllegalArgumentException();
                                else{
                                    // Calculate
                                    if (time == 0.0)
                                        initialVelocity = velocity;

                                    else
                                        initialVelocity = velocity - (acceleration * time);
                                }
                                // I took the absolute value in case the initial velocity is negative
                                if (distance != Math.abs((velocity + initialVelocity)/2) * time)
                                    throw new IllegalArgumentException();
                                else
                                    // Return value
                                    txtInitialVelocity.setText(twoPlaces.format(initialVelocity));
                            }
                            // Velocity is empty
                            else if (txtVelocity.getText().isEmpty()){
                                initialVelocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtInitialVelocity.getText())));
                                distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                                acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                                time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));
                                // Can't have negative time or distance
                                if (time < 0.0 || distance < 0.0) throw new IllegalArgumentException();
                                // If either time or distance is 0 and the other isn't, there's an error
                                if ((time == 0.0 && distance != 0.0) || (time != 0.0 && distance == 0.0)) throw new IllegalArgumentException();
                                else{
                                    // Calculate
                                    if (time == 0.0)
                                        velocity = initialVelocity;

                                    else
                                        velocity = initialVelocity + (acceleration * time);
                                }
                                // Check value makes sense
                                if (distance != Math.abs((velocity + initialVelocity)/2) * time) throw new IllegalArgumentException();
                                else
                                    // Return value
                                    txtVelocity.setText(twoPlaces.format(velocity));
                            }
                            // Distance is empty
                            else if (txtDistance.getText().isEmpty()){
                                initialVelocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtInitialVelocity.getText())));
                                velocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtVelocity.getText())));
                                acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                                time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));

                                // Can't have negative time
                                if (time < 0.0) throw new IllegalArgumentException();
                                if (time == 0.0)
                                    distance = 0.0;

                                else
                                    distance = ((velocity + initialVelocity)/2.0) * time;

                                // Check the values make sense
                                if (velocity != initialVelocity + (acceleration * time)) throw new IllegalArgumentException();
                                else
                                    txtDistance.setText(twoPlaces.format(distance));
                            }
                            // Acceleration is empty
                            else if (txtAcceleration.getText().isEmpty()){
                                initialVelocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtInitialVelocity.getText())));
                                velocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtVelocity.getText())));
                                distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                                time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));

                                // Can't have negative time or distance
                                if (time < 0.0 || distance < 0.0) throw new IllegalArgumentException();
                                // If time or distance is 0 but the other isn't there's an error
                                if ((time == 0.0  && distance != 0.0) || (time != 0.0 && distance == 0.0)) throw new IllegalArgumentException();

                                else
                                    acceleration = (velocity - initialVelocity)/time;


                                if (velocity != initialVelocity + (acceleration * time)) throw new IllegalArgumentException();
                                else
                                    txtAcceleration.setText(twoPlaces.format(acceleration));
                            }
                            // Time is the only case left
                            else{
                                initialVelocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtInitialVelocity.getText())));
                                velocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtVelocity.getText())));
                                distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                                acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));

                                // Can't have negative distance
                                if (distance < 0.0) throw new IllegalArgumentException();
                                else
                                    if (velocity + initialVelocity == 0)
                                        time = (velocity - initialVelocity) / acceleration;
                                    else
                                        time = (2 * distance) / (velocity + initialVelocity);

                                if (Math.pow(velocity,2) != ((Math.pow(initialVelocity,2) + (2*acceleration*distance)))) throw new IllegalArgumentException();
                                else
                                    txtTime.setText(twoPlaces.format(time));


                            }
                        }
                        catch (IllegalArgumentException iae){
                            JOptionPane.showMessageDialog(frame, "Error: Invalid input");
                        }
                        catch(ParseException pe){
                            JOptionPane.showMessageDialog(frame, "Error: Not a number");
                        }


                        break;
                    }
                    // 2 Variables missing
                    case 2:{
                        try {
                            // As i go down the list of checking each field, I initialize the variables
                            // So by the time I reach the last field the assignment is fine

                            // All cases using initial velocity
                            if (!txtInitialVelocity.getText().isEmpty()) {
                                // Assign value to initial velocity
                                initialVelocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtInitialVelocity.getText())));
                                // Initial Velocity and velocity
                                if (!txtVelocity.getText().isEmpty()){
                                    // Assign value to velocity
                                    velocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtVelocity.getText())));
                                    // Initial velocity, velocity, distance
                                    if(!txtDistance.getText().isEmpty()){
                                        distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                                        // Can't have negative distance
                                        if (distance < 0.0) throw new IllegalArgumentException();
                                        else{
                                            /*
                                            * If the difference in velocity is 0 we can't use the fourth equation to find time (division by 0)
                                            * and we can't use the third because acceleration then has to be 0. This can't be because velocity
                                            * is changing between two points and a certain distance, and plugging the values back in will not yield the same results
                                            * And if the distance is 0 but the velocities are different there's an error
                                            * */
                                            if (velocity + initialVelocity == 0.0 && (velocity < 0.0 || initialVelocity < 0.0)) throw new IllegalArgumentException();

                                            // The reason this can't be solved here but can be solved below is because of the format of some of the equations.
                                            // Some lead to division by 0. below, we don't have that issue
                                            else if ((velocity != initialVelocity) && distance == 0.0) throw new IllegalArgumentException();
                                            /*
                                            * If the velocities are equal, I have to use a different set of equations to find the missing values
                                            * */
                                            else if ((velocity == initialVelocity) && distance != 0.0){
                                                acceleration = (Math.pow(velocity,2) - Math.pow(initialVelocity,2))/ distance;
                                                time = (2 * distance)/(velocity + initialVelocity);
                                                txtAcceleration.setText(twoPlaces.format(acceleration));
                                                txtTime.setText(twoPlaces.format(time));
                                            }
                                            else{
                                                // Calculate time
                                                if ((velocity == initialVelocity) && distance == 0){
                                                    time = 0;
                                                    acceleration = 0;
                                                }
                                                else {
                                                    time = 2 * distance / (velocity + initialVelocity);

                                                    // Calculate acceleration
                                                    acceleration = (velocity - initialVelocity) / time;
                                                }
                                                // Display values up to 2 decimal places
                                                txtTime.setText(twoPlaces.format(time));
                                                txtAcceleration.setText(twoPlaces.format(acceleration));
                                            }
                                        }
                                    }
                                    // Initial velocity, velocity and acceleration
                                    else if(!txtAcceleration.getText().isEmpty()){
                                        acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));

                                        // Like before there's no case where I can solve without dividing by 0
                                        if(acceleration == 0.0) throw new IllegalArgumentException();

                                        else {
                                            // Compute values
                                            distance = (Math.pow(velocity, 2) - Math.pow(initialVelocity, 2)) / (2 * acceleration);
                                            time = (velocity - initialVelocity) / acceleration;

                                            // Display values
                                            txtDistance.setText(twoPlaces.format(distance));
                                            txtTime.setText(twoPlaces.format(time));
                                        }
                                    }
                                    // Initial velocity, velocity, and time
                                    else if(!txtTime.getText().isEmpty()){
                                        time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));
                                        // Time can't be negative
                                        if (time < 0.0) throw new IllegalArgumentException();
                                        if (time == 0.0 && (initialVelocity != velocity)) throw new IllegalArgumentException();
                                        else {
                                            if (time == 0.0){
                                                distance = 0.0;
                                                acceleration = 0.0;
                                            }
                                            else {
                                                distance = ((initialVelocity + velocity) / 2.0) * time;
                                                if (velocity == initialVelocity)
                                                    acceleration = 0.0;
                                                else {
                                                    acceleration = (velocity - initialVelocity) / time;
                                                }
                                            }
                                            txtDistance.setText(twoPlaces.format(distance));
                                            txtAcceleration.setText(twoPlaces.format(acceleration));
                                        }

                                    }
                                }
                                // Initial Velocity and distance
                                else if (!txtDistance.getText().isEmpty()){
                                    distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                                    // Initial velocity, Distance and Acceleration
                                    if (!txtAcceleration.getText().isEmpty()){
                                        // If the acceleration is 0, it implies that the two velocities are equal
                                        // If that isn't the case throw an exception

                                        acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                                        // Take the square root to actually get velocity
                                        velocity = Math.sqrt(Math.pow(initialVelocity, 2) + (2 * acceleration * distance));
                                        time = (2 * distance) / (velocity + initialVelocity);

                                        // I check here because i won't be able to tell until after computation
                                        if(Double.isInfinite(time)) throw new IllegalArgumentException();

                                        txtVelocity.setText(twoPlaces.format(velocity));
                                        txtTime.setText(twoPlaces.format(time));
                                    }
                                    // Initial velocity, distance, and Time
                                    else if(!txtTime.getText().isEmpty()){
                                        time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));
                                        // Time can't be negative
                                        if (time < 0.0) throw new IllegalArgumentException();
                                        if (time == 0.0 && (distance != 0.0)) throw new IllegalArgumentException();
                                        else{
                                            // If time and distance are both 0, then the object hasn't moved or changed velocity
                                            // So velocity stays the same as the initial velocity and acceleration is 0
                                            if (time == 0.0 && distance == 0.0){
                                                velocity = initialVelocity;
                                                acceleration = 0;
                                            }
                                            // Otherwise compute the values with the equations
                                            else{
                                                velocity = ((2.0 * distance)/ time) - initialVelocity;
                                                acceleration = (velocity - initialVelocity)/time;
                                            }
                                        }
                                        // Return values
                                        txtVelocity.setText(twoPlaces.format(velocity));
                                        txtAcceleration.setText(twoPlaces.format(acceleration));
                                    }
                                }
                                // Initial velocity, acceleration, and time
                                else if (!txtAcceleration.getText().isEmpty()){
                                    acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                                    // I don't have to check time here given it's the only vase remaining involving initial velocity
                                    time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));

                                    // Time can't be negative
                                    if (time < 0.0) throw new IllegalArgumentException();
                                    if (time == 0.0 && acceleration != 0.0) throw new IllegalArgumentException();
                                    if (time == 0.0 && acceleration == 0.0) {
                                        velocity = initialVelocity;
                                        distance = 0;
                                    }
                                    else {
                                        velocity = initialVelocity + (acceleration * time);
                                        distance = (initialVelocity * time) + ((Math.pow(time, 2) * acceleration) / 2);
                                    }

                                    txtVelocity.setText(twoPlaces.format(velocity));
                                    txtDistance.setText(twoPlaces.format(distance));
                                }

                            }
                            // All cases remaining involving velocity
                            else if (!txtVelocity.getText().isEmpty()){
                                // Assign value to velocity
                                velocity = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtVelocity.getText())));
                                // Velocity and distance
                                if (!txtDistance.getText().isEmpty()){
                                    // Assign value to distance
                                    distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                                    // Can't have negatie distance
                                    if (distance < 0.0) throw new IllegalArgumentException();
                                    // Velocity, distance, and acceleration
                                    if (!txtAcceleration.getText().isEmpty()){
                                        acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                                        if (Math.pow(velocity,2) - (2 * acceleration * distance) < 0) throw new IllegalArgumentException();
                                        else
                                        initialVelocity = Math.sqrt(Math.pow(velocity,2) - (2 * acceleration * distance));
                                        // If acceleration is 0, the velocities must be equal
                                        if (acceleration == 0.0 && velocity != initialVelocity) throw new IllegalArgumentException();
                                        // Can't divide by 0
                                        if (acceleration == 0.0)
                                            time = distance/initialVelocity;
                                        // Solve with first equation
                                        else
                                            time = velocity - initialVelocity/acceleration;

                                        // Return values
                                        txtInitialVelocity.setText(twoPlaces.format(initialVelocity));
                                        txtTime.setText(twoPlaces.format(time));

                                    }
                                    // Velocity, distance, and time
                                    else if (!txtTime.getText().isEmpty()){
                                        time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));
                                        if (time < 0.0) throw new IllegalArgumentException();
                                        if (time == 0.0 && distance != 0.0) throw new IllegalArgumentException();
                                        if (time == 0.0 && distance == 0.0){
                                            initialVelocity = velocity;
                                            acceleration = 0.0;
                                        }
                                        else{
                                            initialVelocity = ((distance * 2) / time) - velocity;
                                            acceleration = (velocity-initialVelocity) / time;
                                        }

                                        // Return values
                                        txtInitialVelocity.setText(twoPlaces.format(initialVelocity));
                                        txtAcceleration.setText(twoPlaces.format(acceleration));
                                    }
                                }
                                // Velocity and time
                                else if (!txtAcceleration.getText().isEmpty()){
                                    // Assign value to acceleration
                                    acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                                    if (!txtTime.getText().isEmpty()){
                                        time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));
                                        if (time < 0.0) throw new IllegalArgumentException();
                                        initialVelocity = velocity - (acceleration * time);
                                        distance = ((velocity + initialVelocity)/2) * time;

                                        txtInitialVelocity.setText(twoPlaces.format(initialVelocity));
                                        // Because distance can be negative I took the absolute value
                                        txtDistance.setText(twoPlaces.format(Math.abs(distance)));
                                    }
                                }
                            }
                            // Final possible case: distance, acceleration, time
                            else{
                                distance = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtDistance.getText())));
                                acceleration = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtAcceleration.getText())));
                                time = Double.parseDouble(twoPlaces.format(twoPlaces.parse(txtTime.getText())));
                                // Can't have negative distance or time
                                if(time < 0.0 || distance < 0.0) throw new IllegalArgumentException();

                                if ((time == 0.0 && distance != 0.0) || (time != 0.0 && distance == 0.0)) throw new  IllegalArgumentException();
                                if (time == 0.0 && distance == 0.0){
                                    initialVelocity = 0;
                                    velocity = 0;
                                }
                                else {
                                    initialVelocity = (distance/time) - ((acceleration * time)/2);
                                    velocity = initialVelocity + (acceleration * time);
                                }

                                // Return the values
                                txtInitialVelocity.setText(twoPlaces.format(initialVelocity));
                                txtVelocity.setText(twoPlaces.format(velocity));
                            }
                        }

                        catch (IllegalArgumentException iae){
                            JOptionPane.showMessageDialog(frame,"Error: Cannot compute with given values");
                        }
                        
                        catch (ParseException e){
                            JOptionPane.showMessageDialog(frame,"Error: Not a number");
                        }

                        break;
                    }
                    // 3 variables missing
                    case 3:{
                        JOptionPane.showMessageDialog(frame, "Not enough information, try again");
                        break;
                    }
                    // 4 variables missing
                    case 4:{
                        JOptionPane.showMessageDialog(frame, "Not enough information, try again");
                        break;
                    }
                    // All variables missing
                    case 5:{
                        JOptionPane.showMessageDialog(frame, "All fields empty, try again");
                        break;
                    }
                }
            }
        });

        frame.setVisible(true);

    }
}

/**
 * The class Main instantiates the GUI and holds the main method of the project
 */

public class Main {
    public static void main(String[] args) throws IOException {
        KinematicGUI gui = new KinematicGUI();
    }
}
