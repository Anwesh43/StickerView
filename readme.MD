## StickerView

### Android module to add a stickerview in your app. A sticker view consists of a image to which you can add sticker's from a container to the image.

### Usage

#### Creating a StickerLayout in your Activity

```
    StickerLayout stickerLayout = new StickerLayout(this);
```

#### Adding a main image in stickerLayout

```
    stickerLayout.setMainImage(bitmap);
```

#### Adding stickers to the container. stickers is a list of bitmap

```
    stickerLayout.setStickers(stickers)
```

#### Showing StickerLayout

```
    stickerLayout.show();
```

#### Demo

<img src="https://github.com/Anwesh43/StickerView/blob/master/screencast/stickerview.gif" width="300px" height="500px" alt="demo of gif">
