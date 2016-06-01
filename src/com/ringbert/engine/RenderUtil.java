package com.ringbert.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

public class RenderUtil {
	
	public static void clearScreen(){
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void setTexture(boolean enabled){
		
		if(enabled){
			glEnable(GL_TEXTURE_2D);
		}
		else{
			glEnable(GL_TEXTURE_2D);
		}
		
	}
	
	public static void unbindTextures()
		{
			glBindTexture(GL_TEXTURE_2D, 0);
		}
	
	public static void setClearColor(Vector3f color){
		glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
	}

	public static void initGraphics(){
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_DEPTH_CLAMP);
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public static String getOpenGLVersion(){
		return glGetString(GL_VERSION);
	}
}
