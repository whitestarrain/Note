# Gui Features in OpenCV

> Here you will learn how to display and save images and videos, control mouse events and create trackbar.

## Getting Started with Images

> Learn to load an image, display it, and save it back


### example code

```python
  import cv2 as cv
  import sys
  # load an image，cv::Mat object
  img = cv.imread(cv.samples.findFile("./temp/image/temp.png"))
  if img is None:
      sys.exit("Could not read the image.")
  # display it
  cv.imshow("Display window", img)
  # wait key
  k = cv.waitKey(0) # show time,0: don't close
  if k == ord("s"):
      # save it
      cv.imwrite("starry_night.png", img)
```

### function

- imread(image_path,format)
  - image path
  - format way
    - cv2.IMREAD_COLOR loads the image in the **BGR 8-bit format**(not RGB!!). This is the default that is used here.
    - cv2.IMREAD_UNCHANGED loads the image as is (including the alpha channel if present)
    - cv2.IMREAD_GRAYSCALE loads the image as an intensity one

- imshow(title_of_window,img)

- cv.waitKey(wait_time)
  - how long should it wait for a user input
  - if `0`:displayed until the user presses a key 
  - return pressed key

- imrite(image_name,img)

## Getting Started with Videos

> Learn to play videos, capture videos from a camera, and write videos

### expample code

> **Make sure a proper version of ffmpeg or gstreamer is installed.**
> **Sometimes it is a headache to work with video capture, mostly due to wrong installation of ffmpeg/gstreamer.** 

#### Capture Video from Camera

```python
  import numpy as np
  import cv2 as cv
  # create a VideoCapture object
  cap = cv.VideoCapture(0)
  if not cap.isOpened():
      print("Cannot open camera")
      exit()
  while True:
      # Capture frame-by-frame
      ret, frame = cap.read()
      # if frame is read correctly ret is True
      if not ret:
          print("Can't receive frame (stream end?). Exiting ...")
          break
      # Our operations on the frame come here
      gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)
      # Display the resulting frame
      cv.imshow('frame', gray)
      if cv.waitKey(1) == ord('q'):
          break
  # When everything done, release the capture
  cap.release()
  cv.destroyAllWindows()
```

#### Playing Video from file

```python
  import numpy as np
  import cv2 as cv
  cap = cv.VideoCapture('vtest.avi')
  while cap.isOpened():
      ret, frame = cap.read()
      # if frame is read correctly ret is True
      if not ret:
          print("Can't receive frame (stream end?). Exiting ...")
          break
      gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)
      cv.imshow('frame', gray)
      if cv.waitKey(1) == ord('q'):
          break
  cap.release()
  cv.destroyAllWindows()
```

#### saving a video

```python
  # capture a video and process it frame-by-frame, and we want to save that video
  import numpy as np
  import cv2 as cv
  cap = cv.VideoCapture(0)
  # Define the codec and create VideoWriter object
  fourcc = cv.VideoWriter_fourcc(*'XVID') # or cv.VideoWriter_fourcc('X','V','I','D')
  out = cv.VideoWriter('output.avi', fourcc, 20.0, (640,  480))
  while cap.isOpened():
      ret, frame = cap.read()
      if not ret:
          print("Can't receive frame (stream end?). Exiting ...")
          break
      frame = cv.flip(frame, 0)
      # write the flipped frame
      out.write(frame)
      cv.imshow('frame', frame)
      if cv.waitKey(1) == ord('q'):
          break
  # Release everything if job is finished
  cap.release()
  out.release()
  cv.destroyAllWindows()
```

### function

- VideoCapture(deviceIndex_or_videoFile)
  - device index 
    - the number to specify which camera
    - Normally one camera will be connected
    - just simply pass 0 (or -1). 
    - can select the second camera by passing 1 and so on
  - the name of a video file

- cap.isopen()
  - check whether capture is initialized or not
  - if not,use 'cap.open()'

- cap.get(video_property_id)
  - For example, 
    - width : `cap.get(cv.CAP_PROP_FRAME_WIDTH)` 
    - height :`cap.get(cv.CAP_PROP_FRAME_HEIGHT)`
  - [cv::VideoCapture::get()](https://docs.opencv.org/4.x/d8/dfe/classcv_1_1VideoCapture.html#aa6480e6972ef4c00d74814ec841a2939)
  - [18 properties](https://docs.opencv.org/4.x/d4/d15/group__videoio__flags__base.html#gaeb8dd9c89c10a5c63c139bf7c4f5704d)

- cap.read()
  - returns a bool (True/False)
  - If the frame is read correctly, it will be True.
  - So you can check for the end of the video by checking this returned value. 

 - cv.cvtColor(frame, ColorConversionCodes)
  - Converts an image from one color space to another.
  - [ColorConversionCodes](https://docs.opencv.org/4.x/d8/d01/group__imgproc__color__conversions.html#ga4e0972be5de079fed4e3a10e24ef5ef0)

- cv.VideoWriter(file_name,fourcc,number_of_frames_per_second(fps),frame_size)
  - fourcc code example:
    - In Fedora: DIVX, XVID, MJPG, X264, WMV1, WMV2. (XVID is more preferable. MJPG results in high size video. X264 gives very small size video)
    - In Windows: DIVX (More to be tested and added)
    - In OSX: MJPG (.mp4), DIVX (.avi), X264 (.mkv).
  - [The list of available fourcc codes](fourcc.org)

- cv.destroyAllWindows()

## Drawing Functions in OpenCV

> Learn to draw lines, rectangles, ellipses, circles, etc with OpenCV


### example code

#### Drawing Line

To draw a line, you need to pass starting and ending coordinates of line. 

We will create a black image and draw a blue line on it from top-left to bottom-right corners.

```python
  import numpy as np
  import cv2 as cv
  # Create a black image
  img = np.zeros((512,512,3), np.uint8)
  # Draw a diagonal blue line with thickness of 5 px
  cv.line(img,(0,0),(511,511),(255,0,0),5)
```

#### Drawing Rectangle

To draw a rectangle, you need top-left corner and bottom-right corner of rectangle.

This time we will draw a green rectangle at the top-right corner of image.

```python
cv.rectangle(img,(384,0),(510,128),(0,255,0),3)
```

#### Drawing Circle

To draw a circle, you need its center coordinates and radius. We will draw a circle inside the rectangle drawn above.

```python
cv.circle(img,(447,63), 63, (0,0,255), -1)
```

#### Drawing Ellipse

To draw the ellipse, we need to pass several arguments.

- One argument is the center location (x,y).
- Next argument is axes lengths (major axis length, minor axis length).
- angle is the angle of rotation of ellipse in anti-clockwise direction.
- startAngle and endAngle denotes the starting and ending of ellipse arc measured in clockwise direction from major axis. i.e. giving values 0 and 360 gives the full ellipse. 

For more details, check the documentation of `cv.ellipse()`. Below example draws a half ellipse at the center of the image.

```python
cv.ellipse(img,(256,256),(100,50),0,0,180,255,-1)
```
#### Drawing Polygon

To draw a polygon, first you need coordinates of vertices.

Make those points into an array of shape ROWSx1x2 where ROWS are number of vertices and it should be of type int32.

Here we draw a small polygon of with four vertices in yellow color.

```python
pts = np.array([[10,5],[20,30],[70,20],[50,10]], np.int32)
pts = pts.reshape((-1,1,2))
cv.polylines(img,[pts],True,(0,255,255))
```

#### Adding Text to Images:

To put texts in images, you need specify following things.

- Text data that you want to write
- Position coordinates of where you want put it (i.e. bottom-left corner where data starts).
- Font type (Check cv.putText() docs for supported fonts)
- Font Scale (specifies the size of font)
- regular things like color, thickness, lineType etc. For better look, lineType = cv.LINE_AA is recommended. <a href="#line_type">line_type</a>

```python
  font = cv.FONT_HERSHEY_SIMPLEX
  # We will write OpenCV on our image in white color.
  cv.putText(img,'OpenCV',(10,500), font, 4,(255,255,255),2,cv.LINE_AA)
```

#### result

![opencv-1](./image/opencv-1.png)

### function

#### common

- function
  - cv.line()
  - cv.circle() 
  - cv.rectangle()
  - cv.ellipse()
  - cv.putText() 
- args:
  - img : The image where you want to draw the shapes
  - color : Color of the shape. for BGR, pass it as a tuple, eg: (255,0,0) for blue. For grayscale, just pass the scalar value.
  - thickness : Thickness of the line or circle etc. If -1 is passed for closed figures like circles, it will fill the shape. default thickness = 1
  - [linetype](https://docs.opencv.org/4.x/d6/d6e/group__imgproc__draw.html#gaf076ef45de481ac96e0ab3dc2c29a777): <a name="line_type" id="line_type"></a>
    - Type of line, whether 8-connected, anti-aliased line etc. By default, it is 8-connected
    - cv.LINE_AA gives anti-aliased line which looks great for curves.

#### others

- cv.polygon(img,points,is_closed,color)
  - If third argument is False, you will get a polylines joining all the points, not a closed shape.
  - cv.polylines() can be used to draw multiple lines. 
    > - cv.ellipse(	img, center, axes, angle, startAngle, endAngle, color[, thickness[, lineType[, shift]]]	) ->	img
    > - cv.ellipse(	img, box, color[, thickness[, lineType]]	) ->	img
    - Just create a list of all the lines you want to draw and pass it to the function.
    - All lines will be drawn individually.
    - It is a much better and faster way to draw a group of lines than calling cv.line() for each line.

- cv.text()
  > cv.putText(	img, text, org, fontFace, fontScale, color[, thickness[, lineType[, bottomLeftOrigin]]]	) ->	img

## Get Start With Event

> Mouse as a Paint-Brush: Draw stuff with your mouse

### example code

#### Simple Demo

**Here, we create a simple application which draws a circle on an image wherever we double-click on it.**

- create a mouse callback function which is executed when a mouse event take place
  - Mouse event can be anything related to mouse like left-button down, left-button up, left-button double-click etc.
  - It gives us the coordinates (x,y) for every mouse event.
  - With this event and location, we can do whatever we like.

- all event:
  ```python
  import cv2 as cv
  events = [i for i in dir(cv) if 'EVENT' in i]
  print( events )
  ```

- Creating mouse callback function has a specific format which is same everywhere.
  - It differs only in what the function does. 
  - So our mouse callback function does one thing, it draws a circle where we double-click.
  - So see the code below. Code is self-explanatory from comments :

  ```python
  import numpy as np
  import cv2 as cv
  # mouse callback function
  def draw_circle(event,x,y,flags,param):
      if event == cv.EVENT_LBUTTONDBLCLK:
          cv.circle(img,(x,y),100,(255,0,0),-1)
  # Create a black image, a window and bind the function to window
  img = np.zeros((512,512,3), np.uint8)
  cv.namedWindow('image')
  cv.setMouseCallback('image',draw_circle) # bind function in `image` window
  while(1):
      cv.imshow('image',img)
      # 20ms is update frequency
      if cv.waitKey(20) & 0xFF == 27: # key esc to quit
          break
  cv.destroyAllWindows()
  ```

#### More Advanced Demo

**draw either rectangles or circles (depending on the mode we select) by dragging the mouse like we do in Paint application**

- mouse callback function has two parts
  - one to draw rectangle and other to draw the circles
  - This specific example will be really helpful in creating and understanding some interactive applications like object tracking, image segmentation etc.

- draw_function
  ```python
  import numpy as np
  import cv2 as cv
  drawing = False # true if mouse is pressed
  mode = True # if True, draw rectangle. Press 'm' to toggle to curve
  ix,iy = -1,-1
  # mouse callback function
  def draw_circle(event,x,y,flags,param):
      global ix,iy,drawing,mode
      if event == cv.EVENT_LBUTTONDOWN:
          drawing = True
          ix,iy = x,y
      elif event == cv.EVENT_MOUSEMOVE:
          if drawing == True:
              if mode == True:
                  cv.rectangle(img,(ix,iy),(x,y),(0,255,0),-1)
              else:
                  cv.circle(img,(x,y),5,(0,0,255),-1)
      elif event == cv.EVENT_LBUTTONUP:
          drawing = False
          if mode == True:
              cv.rectangle(img,(ix,iy),(x,y),(0,255,0),-1)
          else:
              cv.circle(img,(x,y),5,(0,0,255),-1)
  ```

- bind this mouse callback function to OpenCV window
  - In the main loop, we should set a keyboard binding for key 'm' to toggle between rectangle and circle.

  ```python
    img = np.zeros((512,512,3), np.uint8)
    cv.namedWindow('image')
    cv.setMouseCallback('image',draw_circle)
    while(1):
        cv.imshow('image',img)
        k = cv.waitKey(1) & 0xFF
        if k == ord('m'):
            mode = not mode
        elif k == 27:
            break
    cv.destroyAllWindows()
  ```

### function

- cv.namedWindow(window_name)
- cv.setMouseCallback(window_name,fun)

## Exercise:Trackbar as the Color Palette

> Create trackbar to control certain parameters

### example code

```python
  import numpy as np
  import cv2 as cv
  def nothing(x):
      pass
  # Create a black image, a window
  img = np.zeros((300,512,3), np.uint8)
  cv.namedWindow('image')
  # create trackbars for color change
  cv.createTrackbar('R','image',0,255,nothing) # (bar_name,in_which_window,initial_value,max_value,callback)
  cv.createTrackbar('G','image',0,255,nothing)
  cv.createTrackbar('B','image',0,255,nothing)
  # create switch for ON/OFF functionality
  switch = '0 : OFF \n1 : ON'
  cv.createTrackbar(switch, 'image',0,1,nothing)
  while(1):
      cv.imshow('image',img)
      k = cv.waitKey(1) & 0xFF
      if k == 27:
          break
      # get current positions of four trackbars
      r = cv.getTrackbarPos('R','image')
      g = cv.getTrackbarPos('G','image')
      b = cv.getTrackbarPos('B','image')
      s = cv.getTrackbarPos(switch,'image')
      if s == 0:
          img[:] = 0
      else:
          img[:] = [b,g,r]
  cv.destroyAllWindows()
```

### function

- cv.getTrackbarPos()
- cv.createTrackbar() 

# Core Operations

> In this section you will learn basic operations on image like pixel editing, geometric transformations, code optimization, some mathematical tools etc.

## Basic Operations on Images

> Learn to read and edit pixel values, working with image ROI and other basic operations.

## Arithmetic Operations on Images

> Perform arithmetic operations on images

## Performance Measurement and Improvement Techniques

> Getting a solution is important. But getting it in the fastest way is more important. Learn to check the speed of your code, optimize the code etc.

# Image Processing in OpenCV

In this section you will learn different image processing functions inside OpenCV.

# Feature Detection and Description

In this section you will learn about feature detectors and descriptors

# Video analysis (video module)

In this section you will learn different techniques to work with videos like object tracking etc.

# Camera Calibration and 3D Reconstruction

In this section we will learn about camera calibration, stereo imaging etc.

# Machine Learning

In this section you will learn different image processing functions inside OpenCV.

# Computational Photography

In this section you will learn different computational photography techniques like image denoising etc.

# Object Detection (objdetect module)

In this section you will learn object detection techniques like face detection etc.

# OpenCV-Python Bindings

In this section, we will see how OpenCV-Python bindings are generated

# refs

- [OpenCV-Python Tutorials](https://docs.opencv.org/4.x/d6/d00/tutorial_py_root.html)
- [OpenCV Image Processing doc](https://docs.opencv.org/4.x/d7/dbd/group__imgproc.html)
- [如何理解图像深度- 8bit、16bit、24bit、32bi](https://blog.csdn.net/qq_41498261/article/details/104898045)
