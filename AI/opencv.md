# 1. Gui Features in OpenCV

> Here you will learn how to display and save images and videos, control mouse events and create trackbar.

## 1.1. Getting Started with Images

> Learn to load an image, display it, and save it back

### 1.1.1. example code

```python
  import cv2 as cv
  import sys
  # load an image
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

### 1.1.2. function

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

## 1.2. Getting Started with Videos

> Learn to play videos, capture videos from a camera, and write videos

### 1.2.1. expample code

> **Make sure a proper version of ffmpeg or gstreamer is installed.**
> **Sometimes it is a headache to work with video capture, mostly due to wrong installation of ffmpeg/gstreamer.** 

#### 1.2.1.1. Capture Video from Camera

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

#### 1.2.1.2. Playing Video from file

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

#### 1.2.1.3. saving a video

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

### 1.2.2. function

- VideoCapture(deviceIndex_or_videoFile)
  - device index 
    - the number to specify which camera
    - Normally one camera will be connected
    - just simply pass 0 (or -1). 
    - can select the second camera by passing 1 and so on
  - the name of a video file

- cap.read()
  - retunr (is_read_correctly,frame_img)

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
  - **can convert BGR to RGB**
  - [ColorConversionCodes](https://docs.opencv.org/4.x/d8/d01/group__imgproc__color__conversions.html#ga4e0972be5de079fed4e3a10e24ef5ef0)

- cv.VideoWriter(file_name,fourcc,number_of_frames_per_second(fps),frame_size)
  - fourcc code example:
    - In Fedora: DIVX, XVID, MJPG, X264, WMV1, WMV2. (XVID is more preferable. MJPG results in high size video. X264 gives very small size video)
    - In Windows: DIVX (More to be tested and added)
    - In OSX: MJPG (.mp4), DIVX (.avi), X264 (.mkv).
  - [The list of available fourcc codes](fourcc.org)

- cv.destroyAllWindows()

## 1.3. Drawing Functions in OpenCV

> Learn to draw lines, rectangles, ellipses, circles, etc with OpenCV

### 1.3.1. example code

#### 1.3.1.1. Drawing Line

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

#### 1.3.1.2. Drawing Rectangle

To draw a rectangle, you need top-left corner and bottom-right corner of rectangle.

This time we will draw a green rectangle at the top-right corner of image.

```python
cv.rectangle(img,(384,0),(510,128),(0,255,0),3)
```

#### 1.3.1.3. Drawing Circle

To draw a circle, you need its center coordinates and radius. We will draw a circle inside the rectangle drawn above.

```python
cv.circle(img,(447,63), 63, (0,0,255), -1)
```

#### 1.3.1.4. Drawing Ellipse

To draw the ellipse, we need to pass several arguments.

- One argument is the center location (x,y).
- Next argument is axes lengths (major axis length, minor axis length).
- angle is the angle of rotation of ellipse in anti-clockwise direction.
- startAngle and endAngle denotes the starting and ending of ellipse arc measured in clockwise direction from major axis. i.e. giving values 0 and 360 gives the full ellipse. 

For more details, check the documentation of `cv.ellipse()`. Below example draws a half ellipse at the center of the image.

```python
cv.ellipse(img,(256,256),(100,50),0,0,180,255,-1)
```
#### 1.3.1.5. Drawing Polygon

To draw a polygon, first you need coordinates of vertices.

Make those points into an array of shape ROWSx1x2 where ROWS are number of vertices and it should be of type int32.

Here we draw a small polygon of with four vertices in yellow color.

```python
pts = np.array([[10,5],[20,30],[70,20],[50,10]], np.int32)
pts = pts.reshape((-1,1,2))
cv.polylines(img,[pts],True,(0,255,255))
```

#### 1.3.1.6. Adding Text to Images:

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

#### 1.3.1.7. result

![opencv-1](./image/opencv-1.png)

### 1.3.2. function

#### 1.3.2.1. common

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

#### 1.3.2.2. others

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

## 1.4. Get Start With Event

> Mouse as a Paint-Brush: Draw stuff with your mouse

### 1.4.1. example code

#### 1.4.1.1. Simple Demo

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

#### 1.4.1.2. More Advanced Demo

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

### 1.4.2. function

- cv.namedWindow(window_name)
- cv.setMouseCallback(window_name,fun)

## 1.5. Exercise:Trackbar as the Color Palette

> Create trackbar to control certain parameters

### 1.5.1. example code

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

### 1.5.2. function

- cv.createTrackbar() 
- cv.getTrackbarPos()

# 2. Core Operations

> In this section you will learn basic operations on image like pixel editing, geometric transformations, code optimization, some mathematical tools etc.

## 2.1. Basic Operations on Images(numpy operations)

> Learn to read and edit pixel values, working with image ROI and other basic operations.
>
> - Access pixel values and modify them
> - Access image properties
> - Set a Region of Interest (ROI)
> - Split and merge images

### 2.1.1. expample code

#### 2.1.1.1. Image ROI(Region Of Interest)

```python
import numpy as np
import cv2 as cv

img = cv.imread('messi5.jpg')
ball = img[280:340, 330:390]
img[273:333, 100:160] = ball
```

![opencv-2](./image/opencv-2.png)

#### 2.1.1.2. Splitting and Merging Image Channels

```python
  b,g,r = cv.split(img) # don't use split
  img = cv.merge((b,g,r))
  # or
  b = img[:,:,0]
  g = img[:,:,1]
  r = img[:,:,2]
```

- Notice:
  - cv.split() is a costly operation (in terms of time).
  - So use it only if necessary. Otherwise go for Numpy indexing.

#### 2.1.1.3. Making Borders for Images (Padding)

If you want to create a border around an image, something like a photo frame, you can use `cv.copyMakeBorder()`

But it has more applications for convolution operation, zero padding etc. This function takes following arguments:

```python
  import cv2 as cv
  import numpy as np
  from matplotlib import pyplot as plt
  BLUE = [255,0,0]
  img1 = cv.imread('opencv-logo.png')
  replicate = cv.copyMakeBorder(img1,10,10,10,10,cv.BORDER_REPLICATE)
  reflect = cv.copyMakeBorder(img1,10,10,10,10,cv.BORDER_REFLECT)
  reflect101 = cv.copyMakeBorder(img1,10,10,10,10,cv.BORDER_REFLECT_101)
  wrap = cv.copyMakeBorder(img1,10,10,10,10,cv.BORDER_WRAP)
  constant= cv.copyMakeBorder(img1,10,10,10,10,cv.BORDER_CONSTANT,value=BLUE)
  plt.subplot(231),plt.imshow(img1,'gray'),plt.title('ORIGINAL')
  plt.subplot(232),plt.imshow(replicate,'gray'),plt.title('REPLICATE')
  plt.subplot(233),plt.imshow(reflect,'gray'),plt.title('REFLECT')
  plt.subplot(234),plt.imshow(reflect101,'gray'),plt.title('REFLECT_101')
  plt.subplot(235),plt.imshow(wrap,'gray'),plt.title('WRAP')
  plt.subplot(236),plt.imshow(constant,'gray'),plt.title('CONSTANT')
  plt.show()
```

See the result below. (Image is displayed with matplotlib. So RED and BLUE channels will be interchanged):

![opencv-3](./image/opencv-3.png)

### 2.1.2. function

- cv.split()
  > don't use

- cv.copyMakeBorder()
  - src - input image
  - top, bottom, left, right - border width in number of pixels in corresponding directions
  - borderType - Flag defining what kind of border to be added. It can be following types:
    - cv.BORDER_CONSTANT - Adds a constant colored border. The value should be given as next argument.
    - cv.BORDER_REFLECT - Border will be mirror reflection of the border elements, like this : fedcba|abcdefgh|hgfedcb
    - cv.BORDER_REFLECT_101 or cv.BORDER_DEFAULT - Same as above, but with a slight change, like this : gfedcb|abcdefgh|gfedcba
    - cv.BORDER_REPLICATE - Last element is replicated throughout, like this: aaaaaa|abcdefgh|hhhhhhh
    - cv.BORDER_WRAP - Can't explain, it will look like this : cdefgh|abcdefgh|abcdefg
  - value - Color of border if border type is cv.BORDER_CONSTANT

## 2.2. Arithmetic Operations on Images

> Perform arithmetic operations on images
>
> - Learn several arithmetic operations on images, like addition, subtraction, bitwise operations, and etc.
> - Learn these functions: cv.add(), cv.addWeighted(), etc.

### 2.2.1. expample code

#### 2.2.1.1. Image Addition

- There is a difference between **OpenCV addition** and **Numpy addition**
  - OpenCV addition is a saturated operation 
  - Numpy addition is a modulo operation.

  ```python
  >>> x = np.uint8([250])
  >>> y = np.uint8([10])
  >>> print( cv.add(x,y) ) # 250+10 = 260 => 255
  [[255]]
  >>> print( x+y )          # 250+10 = 260 % 256 = 4
  [4]
  ```

- Stick with OpenCV functions
  - because they will provide a better result.
  - This will be more visible when you add two images.

#### 2.2.1.2. Image Blending

This is also image addition, but different weights are given to images in order to give a feeling of blending or transparency.

- Images are added as per the equation below:
  - $g(x)=(1−α)f_{0}(x)+αf_{1}(x)$ 
  - By varying α from 0→1, you can perform a cool transition between one image to another.

  ```python
  img1 = cv.imread('ml.png')
  img2 = cv.imread('opencv-logo.png')
  # The first image is given a weight of 0.7 and the second image is given 0.3.
  dst = cv.addWeighted(img1,0.7,img2,0.3,0)
  cv.imshow('dst',dst)
  cv.waitKey(0)
  cv.destroyAllWindows()
  Check the result below:
  ```

- result
  ![opencv-4](./image/opencv-4.png)

#### 2.2.1.3. Bitwise Operations

- explain
  - This includes the bitwise AND, OR, NOT, and XOR operations
  - They will be highly useful while extracting any part of the image (as we will see in coming chapters), defining and working with non-rectangular ROI's, and etc.
  - Below we will see an example of how to change a particular region of an image.

- put the OpenCV logo above an image
  - If I add two images, it will change the color. 
  - If I blend them, I get a transparent effect. But I want it to be opaque(不透明的). 
  - If it was a rectangular region, I could use ROI as we did in the last chapter. But the OpenCV logo is a not a rectangular shape. 
  - So you can do it with bitwise operations as shown below:

  ```python
  # Load two images
  img1 = cv.imread('messi5.jpg')
  img2 = cv.imread('opencv-logo-white.png')
  # I want to put logo on top-left corner, So I create a ROI
  rows,cols,channels = img2.shape
  roi = img1[0:rows, 0:cols]
  # Now create a mask of logo and create its inverse mask also
  img2gray = cv.cvtColor(img2,cv.COLOR_BGR2GRAY)
  ret, mask = cv.threshold(img2gray, 10, 255, cv.THRESH_BINARY) # Later chapters will explain 'threshold' 
  mask_inv = cv.bitwise_not(mask) # 0->255,255->0
  # Now black-out the area of logo in ROI
  img1_bg = cv.bitwise_and(roi,roi,mask = mask_inv)
  # Take only region of logo from logo image.
  img2_fg = cv.bitwise_and(img2,img2,mask = mask)
  # Put logo in ROI and modify the main image
  dst = cv.add(img1_bg,img2_fg)

  plt.figure(figsize = (10,10))
  def plt_bgr(img):
      plt.imshow(cv.cvtColor(img, cv.COLOR_BGR2RGB))
  plt.subplot(331),plt_bgr(img1),plt.title("img1")
  plt.subplot(332),plt_bgr(img2),plt.title("img2")
  plt.subplot(333),plt_bgr(roi),plt.title("roi")
  plt.subplot(334),plt_bgr(img2gray),plt.title("img2gray")
  plt.subplot(335),plt_bgr(mask),plt.title("mask")
  plt.subplot(336),plt_bgr(mask_inv),plt.title("mask_inv")
  plt.subplot(337),plt_bgr(img1_bg),plt.title("img1_bg")
  plt.subplot(338),plt_bgr(img2_fg),plt.title("img2_fg")
  plt.subplot(339),plt_bgr(dst),plt.title("dst")

  img1[0:rows, 0:cols ] = dst
  cv.imshow('res',img1)
  cv.waitKey(0)
  cv.destroyAllWindows()
  ```
  ![opencv-6](./image/opencv-6.png)

  ![opencv-5](./image/opencv-5.png)

  > See the result above. Left image shows the mask we created. Right image shows the final result.
  >
  > For more understanding, display all the intermediate images in the above code, especially img1_bg and img2_fg.

### 2.2.2. function

- cv.add(arg1,arg2)
  > better than numpy add <br/>
  > `cv.add(	src1, src2[, dst[, mask[, dtype]]]	) ->	dst`
- cv.addWeighted(img1,weight1,img2,weight2,offset) 
  - applies the following equation to the image: $dst=α⋅img1+β⋅img2+γ$
- cv.threshold()
  > explain in later chapter
- cv.bitwise_not
- cv.bitwise_and
- cv.bitwise_xor
- cv.bitwise_or

## 2.3. Performance Measurement and Improvement Techniques

> Getting a solution is important. But getting it in the fastest way is more important. Learn to check the speed of your code, optimize the code etc.
>
> - Measure the performance of your code.
> - Some tips to improve the performance of your code.

### 2.3.1. expample code

#### 2.3.1.1. Measuring Performance with OpenCV

- `cv.getTickCount`: 
  - returns the number of clock-cycles after a reference event (like the moment the machine was switched ON) to the moment this function is called. 
  - So if you call it before and after the function execution, you get the number of clock-cycles used to execute a function.
- `cv.getTickFrequency`:
  - returns the cpu's frequency of clock-cycles, or the number of clock-cycles per second.
  - So to find the time of execution in seconds, you can do following:

  ```python
  e1 = cv.getTickCount()
  # your code execution
  e2 = cv.getTickCount()
  time = (e2 - e1)/ cv.getTickFrequency()
  ```

We will demonstrate with following example. The following example applies median filtering with kernels of odd sizes ranging from 5 to 49. (Don't worry about what the result will look like - that is not our goal):

  ```python
  img1 = cv.imread('messi5.jpg')
  e1 = cv.getTickCount()
  for i in range(5,49,2):
      img1 = cv.medianBlur(img1,i)
  e2 = cv.getTickCount()
  t = (e2 - e1)/cv.getTickFrequency()
  print( t )
  # Result I got is 0.521107655 seconds
  ```

- notice:
  - You can do the same thing with the `time` module. 
  - Instead of `cv.getTickCount`, use the time.time() function. Then take the difference of the two times.

#### 2.3.1.2. Default Optimization in OpenCV

- explain
  - Many of the OpenCV functions are optimized using SSE2, AVX, etc
  - It contains the unoptimized code also.
  - So if our system support these features, we should exploit them (almost all modern day processors support them). 
  - It is enabled by default while compiling.
  - So OpenCV runs the optimized code if it is enabled, otherwise it runs the unoptimized code.
  - You can use `cv.useOptimized()` to check if it is enabled/disabled 
  - use `cv.setUseOptimized()` to enable/disable it

- example

  ```python
  # check if optimization is enabled
  In [5]: cv.useOptimized()
  Out[5]: True
  In [6]: %timeit res = cv.medianBlur(img,49)
  10 loops, best of 3: 34.9 ms per loop
  # Disable it
  In [7]: cv.setUseOptimized(False)
  In [8]: cv.useOptimized()
  Out[8]: False
  In [9]: %timeit res = cv.medianBlur(img,49)
  10 loops, best of 3: 64.1 ms per loop
  ```

  - As you can see, optimized median filtering is 2x faster than the unoptimized version.
  - If you check its source, you can see that median filtering is SIMD optimized.
  - So you can use this to enable optimization at the top of your code (remember it is enabled by default).

#### 2.3.1.3. Measuring Performance in IPython

Sometimes you may need to compare the performance of two similar operations. IPython gives you a magic command timeit to perform this.

It runs the code several times to get more accurate results. Once again, it is suitable to measuring single lines of code.

- example(python vs numpy)
  - do you know which of the following addition operations is better,
  - `x = 5; y = x**2, x = 5; y = x*x, x = np.uint8([5]); y = x*x, or y = np.square(x)`?
  - We will find out with timeit in the IPython shell.

  ```python
  In [10]: x = 5
  In [11]: %timeit y=x**2
  10000000 loops, best of 3: 73 ns per loop
  In [12]: %timeit y=x*x
  10000000 loops, best of 3: 58.3 ns per loop
  In [15]: z = np.uint8([5])
  In [17]: %timeit y=z*z
  1000000 loops, best of 3: 1.25 us per loop
  In [19]: %timeit y=np.square(z)
  1000000 loops, best of 3: 1.16 us per loop
  ```

  - You can see that, `x = 5 ; y = x*x` is fastest and it is around 20x faster compared to Numpy.
  - If you consider the array creation also, it may reach up to 100x faster. 

  - Notice
    - Python scalar operations are faster than Numpy scalar operations.
    - So for operations including one or two elements, Python scalar is better than Numpy arrays.
    - **Numpy has the advantage when the size of the array is a little bit bigger**.

- example2(numpy vs open-cv)

  - compare the performance of cv.countNonZero() and np.count_nonzero() for the same image.

  ```python
  In [35]: %timeit z = cv.countNonZero(img)
  100000 loops, best of 3: 15.8 us per loop
  In [36]: %timeit z = np.count_nonzero(img)
  1000 loops, best of 3: 370 us per loop
  ```

  - the OpenCV function is nearly 25x faster than the Numpy function.
  - notice:
    - **Normally, OpenCV functions are faster than Numpy functions**. So for same operation, OpenCV functions are preferred.
    - But, there can be exceptions, **especially when Numpy works with views instead of copies**.

#### 2.3.1.4. Performance Optimization Techniques

There are several techniques and coding methods to exploit maximum performance of Python and Numpy.
Only relevant ones are noted here and links are given to important sources.

- [Python Optimization Techniques](https://wiki.python.org/moin/PythonSpeed/PerformanceTips)
- [Scipy Lecture Notes - Advanced Numpy](https://scipy-lectures.github.io/advanced/advanced_numpy/index.html#advanced-numpy)

The main thing to be noted here is, first try to implement the algorithm in a simple manner.
Once it is working, profile it, find the bottlenecks, and optimize them.

- Avoid using loops in Python as much as possible, especially double/triple loops etc. They are inherently slow.
- Vectorize the algorithm/code to the maximum extent possible, because Numpy and OpenCV are optimized for vector operations.
- Exploit the cache coherence.
- Never make copies of an array unless it is necessary. Try to use views instead. Array copying is a costly operation.

If your code is still slow after doing all of these operations, or if the use of large loops is inevitable, use additional libraries like Cython to make it faster.

### 2.3.2. function

- cv.useOptimized()
- cv.setUseOptimized()

# 3. Image Processing in OpenCV

In this section you will learn different image processing functions inside OpenCV.

## 3.1. Changing Colorspaces

Learn to change images between different color spaces. Plus learn to track a colored object in a video.

### 3.1.1. example code

### 3.1.2. function

## 3.2. Geometric Transformations of Images

Learn to apply different geometric transformations to images like rotation, translation etc.

### 3.2.1. example code

### 3.2.2. function

## 3.3. Image Thresholding

Learn to convert images to binary images using global thresholding, Adaptive thresholding, Otsu's binarization etc

### 3.3.1. example code

### 3.3.2. function

## 3.4. Smoothing Images

Learn to blur the images, filter the images with custom kernels etc.

### 3.4.1. example code

### 3.4.2. function

## 3.5. Morphological Transformations

Learn about morphological transformations like Erosion, Dilation, Opening, Closing etc

### 3.5.1. example code

### 3.5.2. function

## 3.6. Image Gradients

Learn to find image gradients, edges etc.

### 3.6.1. example code

### 3.6.2. function

## 3.7. Canny Edge Detection

Learn to find edges with Canny Edge Detection

### 3.7.1. example code

### 3.7.2. function

## 3.8. Image Pyramids

Learn about image pyramids and how to use them for image blending

### 3.8.1. example code

### 3.8.2. function

## 3.9. Contours in OpenCV

All about Contours in OpenCV

### 3.9.1. example code

### 3.9.2. function

## 3.10. Histograms in OpenCV

All about histograms in OpenCV

### 3.10.1. example code

### 3.10.2. function

## 3.11. Image Transforms in OpenCV

Meet different Image Transforms in OpenCV like Fourier Transform, Cosine Transform etc.

### 3.11.1. example code

### 3.11.2. function

## 3.12. Template Matching

Learn to search for an object in an image using Template Matching

### 3.12.1. example code

### 3.12.2. function

## 3.13. Hough Line Transform

Learn to detect lines in an image

### 3.13.1. example code

### 3.13.2. function

## 3.14. Hough Circle Transform

Learn to detect circles in an image

### 3.14.1. example code

### 3.14.2. function

## 3.15. Image Segmentation with Watershed Algorithm

Learn to segment images with watershed segmentation

### 3.15.1. example code

### 3.15.2. function

## 3.16. Interactive Foreground Extraction using GrabCut Algorithm

Learn to extract foreground with GrabCut algorithm

### 3.16.1. example code

### 3.16.2. function

# 4. Feature Detection and Description

In this section you will learn about feature detectors and descriptors

# 5. Video analysis (video module)

In this section you will learn different techniques to work with videos like object tracking etc.

# 6. Camera Calibration and 3D Reconstruction

In this section we will learn about camera calibration, stereo imaging etc.

# 7. Machine Learning

In this section you will learn different image processing functions inside OpenCV.

# 8. Computational Photography

In this section you will learn different computational photography techniques like image denoising etc.

# 9. Object Detection (objdetect module)

In this section you will learn object detection techniques like face detection etc.

# 10. OpenCV-Python Bindings

In this section, we will see how OpenCV-Python bindings are generated

# 11. refs

- [OpenCV-Python Tutorials](https://docs.opencv.org/4.x/d6/d00/tutorial_py_root.html)
- [OpenCV Image Processing doc](https://docs.opencv.org/4.x/d7/dbd/group__imgproc.html)
- [如何理解图像深度- 8bit、16bit、24bit、32bi](https://blog.csdn.net/qq_41498261/article/details/104898045)
- [2.2. Advanced NumPy](http://scipy-lectures.org/advanced/advanced_numpy/index.html#advanced-numpy)
