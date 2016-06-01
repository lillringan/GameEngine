package com.ringbert.engine;

public class Camera {
	
	public static final Vector3f yAxis = new Vector3f(0, 1, 0);

	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	
	public Camera(){
		this(new Vector3f(0, 0, 0), new Vector3f (0, 0, 1), new Vector3f(0 , 1, 0));
	}
	
	public Camera(Vector3f pos, Vector3f forward, Vector3f up){
		this.pos = pos;
		this.forward = forward.normalized();
		this.up = up.normalized();
	}
	
	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);
	
	public void input(){
		float sensitivity = 0.5f;
		float moveAmt = (float)(10 * Time.getDelta());
		//float rotAmt = (float)(100 * Time.getDelta());
		
		if(Input.GetKey(Input.KEY_ESCAPE)){
			Input.SetCursor(true);
			mouseLocked = false;
		}
		
		if(Input.GetMouse(0)){
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}
		
		if(Input.GetKey(Input.KEY_W))
			move(getForward(), moveAmt);
		if(Input.GetKey(Input.KEY_S))
			move(getForward(), -moveAmt);
		if(Input.GetKey(Input.KEY_A))
			move(getLeft(), moveAmt);
		if(Input.GetKey(Input.KEY_D))
			move(getRight(), moveAmt);
		
		if(mouseLocked)
					{
						Vector2f deltaPos = Input.GetMousePosition().sub(centerPosition);
						
						boolean rotY = deltaPos.getX() != 0;
						boolean rotX = deltaPos.getY() != 0;
						
						if(rotY)
							rotateY(deltaPos.getX() * sensitivity);
						if(rotX)
							rotateX(-deltaPos.getY() * sensitivity);
							
						if(rotY || rotX)
							Input.SetMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
					
			
		}
		
		/*if(Input.GetKey(Input.KEY_UP))
			rotateX(-rotAmt);
		if(Input.GetKey(Input.KEY_DOWN))
			rotateX(rotAmt);
		if(Input.GetKey(Input.KEY_LEFT))
			rotateY(-rotAmt);
		if(Input.GetKey(Input.KEY_RIGHT))
			rotateY(rotAmt);*/
	}
	
	public void move(Vector3f dir, float amt){
		pos = pos.add(dir.mul(amt));
	}
	
	public void rotateY(float angle){
		Vector3f Haxis = yAxis.cross(forward).normalized();
		forward = forward.rotate(angle, yAxis).normalized();
		
		up = forward.cross(Haxis).normalized();
		
	}
	
	public void rotateX(float angle){
		Vector3f Haxis = yAxis.cross(forward).normalized();
		
		forward = forward.rotate(angle, Haxis).normalized();
		
		up = forward.cross(Haxis).normalized();
	}
	
	public Vector3f getLeft(){
		return forward.cross(up).normalized();
	}
	
	public Vector3f getRight(){
		return up.cross(forward).normalized();
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getForward() {
		return forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}
	
}
