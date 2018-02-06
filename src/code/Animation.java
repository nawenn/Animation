package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	String background = "/img/background.png";
	Timer tm;
	JButton pause = new JButton("pasuse");
	JButton start = new JButton("start");
	JButton detail = new JButton("detail");
	Ball ballblue;
	Ball ballred;
	Ball stopAndWait;
	Ball error;
	Message message = new Message();

	List<Integer> bluepath = new ArrayList<Integer>();
	List<Integer> redpath = new ArrayList<Integer>();
	List<Integer> stopAndWaitPath = new ArrayList<Integer>();
	List<Integer> errorpath = new ArrayList<Integer>();
	List<Integer> pathLabel = new ArrayList<Integer>();

	public Animation() {
		//Setting up position of the Balls
		setFocusable(true);
		tm = new Timer(50, this);
		stopAndWait = new Ball(90, 0, "Stop And Wait");
		error = new Ball(90, 0, "Error");
		ballblue = new Ball(90, 0, "Blue Ball");
		ballred = new Ball(90, 20, "Red Ball");
		bluepath = assignedge();
		for (int i = 0; i < bluepath.size() - 1; i++) {
			stopAndWaitPath.add(bluepath.get(i));
		}
		for (int i = 0; i < stopAndWaitPath.size() - 1; i++) {
			errorpath.add(stopAndWaitPath.get(i));
		}

		//printing out the weight of each link on the animation background
		JLabel lr1_r21 = new JLabel(pathLabel.get(0) + "");
		JLabel lr1_r71 = new JLabel(pathLabel.get(1) + "");
		JLabel lr2_r51 = new JLabel(pathLabel.get(2) + "");
		JLabel lr2_r31 = new JLabel(pathLabel.get(3) + "");
		JLabel lr3_r41 = new JLabel(pathLabel.get(4) + "");
		JLabel lr3_r61 = new JLabel(pathLabel.get(5) + "");
		JLabel lr5_r71 = new JLabel(pathLabel.get(6) + "");
		JLabel lr6_r81 = new JLabel(pathLabel.get(7) + "");
		JLabel lr7_r81 = new JLabel(pathLabel.get(8) + "");
		JLabel lr8_r41 = new JLabel(pathLabel.get(9) + "");
		JLabel lr5_r61 = new JLabel(pathLabel.get(10) + "");
		this.add(lr1_r21);
		this.add(lr1_r71);
		this.add(lr2_r51);
		this.add(lr2_r31);
		this.add(lr3_r41);
		this.add(lr3_r61);
		this.add(lr5_r71);
		this.add(lr6_r81);
		this.add(lr7_r81);
		this.add(lr8_r41);
		this.add(lr5_r61);
		
		lr1_r21.setBounds(346, 349, 20, 20);
		lr1_r71.setBounds(317, 555, 20, 20);
		lr2_r51.setBounds(499, 374, 20, 20);
		lr2_r31.setBounds(626, 261, 20, 20);
		lr3_r41.setBounds(986, 352, 20, 20);
		lr3_r61.setBounds(827, 362, 20, 20);
		lr5_r71.setBounds(501, 564, 20, 20);
		lr6_r81.setBounds(882, 527, 20, 20);
		lr7_r81.setBounds(623, 637, 20, 20);
		lr8_r41.setBounds(976, 591, 20, 20);
		lr5_r61.setBounds(644, 456, 20, 20);
		
		//assign random link weights to each link for shortest path
		redpath = assignedge();
		JLabel lr1_r2 = new JLabel(pathLabel.get(0) + "");
		JLabel lr1_r7 = new JLabel(pathLabel.get(1) + "");
		JLabel lr2_r5 = new JLabel(pathLabel.get(2) + "");
		JLabel lr2_r3 = new JLabel(pathLabel.get(3) + "");
		JLabel lr3_r4 = new JLabel(pathLabel.get(4) + "");
		JLabel lr3_r6 = new JLabel(pathLabel.get(5) + "");
		JLabel lr5_r7 = new JLabel(pathLabel.get(6) + "");
		JLabel lr6_r8 = new JLabel(pathLabel.get(7) + "");
		JLabel lr7_r8 = new JLabel(pathLabel.get(8) + "");
		JLabel lr8_r4 = new JLabel(pathLabel.get(9) + "");
		JLabel lr5_r6 = new JLabel(pathLabel.get(10) + "");
		this.add(lr1_r2);
		this.add(lr1_r7);
		this.add(lr2_r5);
		this.add(lr2_r3);
		this.add(lr3_r4);
		this.add(lr3_r6);
		this.add(lr5_r7);
		this.add(lr6_r8);
		this.add(lr7_r8);
		this.add(lr8_r4);
		this.add(lr5_r6);
		this.add(pause);
		this.add(start);
		this.add(detail);
		lr1_r2.setBounds(366, 349, 20, 20);
		lr1_r7.setBounds(337, 555, 20, 20);
		lr2_r5.setBounds(519, 374, 20, 20);
		lr2_r3.setBounds(646, 261, 20, 20);
		lr3_r4.setBounds(1006, 352, 20, 20);
		lr3_r6.setBounds(847, 362, 20, 20);
		lr5_r7.setBounds(521, 564, 20, 20);
		lr6_r8.setBounds(902, 527, 20, 20);
		lr7_r8.setBounds(643, 637, 20, 20);
		lr8_r4.setBounds(996, 591, 20, 20);
		lr5_r6.setBounds(664, 456, 20, 20);

		start.setBounds(520, 7, 80, 30);
		pause.setBounds(620, 7, 80, 30);
		detail.setBounds(720, 7, 80, 30);
		this.setLayout(null);
		
		//set initial message
		ballblue.setMessage("H");
		ballred.setMessage("A");
		
		
		//set the funtion of each button
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tm.stop();
			}
		});

		detail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				message.getLayer(ballblue);
				message.getLayer(ballred);
				message.display(ballblue.getMessage(), ballblue);
				message.display(ballred.getMessage(), ballred);
			}
		});

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tm.start();
			}
		});

		//when mouse clicked print out the x and y values.
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.println(me.getPoint());
			}
		});

	}

	//printing everything out before the animaition starts.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			BufferedImage i = ImageIO.read(getClass().getResource(background));
			i.createGraphics();
			g2d.drawImage(i, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (stopAndWait.getack() == false) {
			g2d.setColor(Color.blue);
		} else {
			g2d.setColor(new Color(0, 0, 0, 0));
		}
		stopAndWait.draw(g2d);
		if (stopAndWait.getack() == false) {
			g2d.setColor(Color.blue);
		} else {
			g2d.setColor(new Color(0, 0, 0, 0));
		}
		error.draw(g2d);
		g2d.setColor(Color.blue);
		ballblue.draw(g2d);
		g2d.setColor(Color.red);
		ballred.draw(g2d);

	}

	//calling the method for ball to run
	public void actionPerformed(ActionEvent e) {
		stopAndWait.update(stopAndWaitPath, "stopAndWait");
		error.update(errorpath, "error");
		ballblue.update(bluepath, "regular");
		ballred.update(redpath, "regular");
		repaint();

	}

	//assigns random weight to each link and run the dijstra algorithm
	public List<Integer> assignedge() {
		Shortestparth graph = new Shortestparth(11);
		Random rand = new Random();
		pathLabel.removeAll(pathLabel);
		for (int i = 1; i <= 11; i++) {
			pathLabel.add(rand.nextInt(7) + 1);
		}

		graph.addEdge(1, 2, pathLabel.get(0));
		graph.addEdge(1, 7, pathLabel.get(1));
		graph.addEdge(2, 5, pathLabel.get(2));
		graph.addEdge(2, 3, pathLabel.get(3));
		graph.addEdge(3, 4, pathLabel.get(4));
		graph.addEdge(3, 6, pathLabel.get(5));
		graph.addEdge(5, 7, pathLabel.get(6));
		graph.addEdge(6, 8, pathLabel.get(7));
		graph.addEdge(7, 8, pathLabel.get(8));
		graph.addEdge(8, 4, pathLabel.get(9));
		graph.addEdge(5, 6, pathLabel.get(10));
		graph.dijkstra();
		graph.path();
		List<Integer> path = new ArrayList<Integer>();
		path.clear();
		for (int i = 0; i < graph.path().size(); i++) {
			path.add(graph.path().get(i));
		}
		return path;
	}
}
