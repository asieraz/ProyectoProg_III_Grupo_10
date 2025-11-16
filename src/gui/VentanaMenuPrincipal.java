package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class VentanaMenuPrincipal extends VentanaAbstracta{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaMenuPrincipal() {
		
		JPanel pNorte = new JPanel();
        JPanel pSur = new JPanel();
        JPanel pCentro = new JPanel();
        JPanel pOeste = new JPanel();
        JPanel pEste = new JPanel();

        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pEste, BorderLayout.EAST);
        getContentPane().add(pOeste, BorderLayout.WEST);
        getContentPane().add(pSur, BorderLayout.SOUTH);
        getContentPane().add(pCentro, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
