/*
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 *
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

// TODO: comment this file explaining its behavior

import acm.graphics.GImage;

public class ImageShopAlgorithms implements ImageShopAlgorithmsInterface {

    public GImage flipHorizontal(GImage source) {
        // TODO
        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;
        int[][] result = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                int px = pixels[r][cols - j - 1];
                result[r][j] = px;
            }
        }
//		source.setPixelArray(pixels);
        GImage newImg = new GImage(result);
        return newImg;
    }

    public GImage rotateLeft(GImage source) {
        // TODO
        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;
        int[][] result = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                int px = pixels[r][j];
                result[cols - j - 1][r] = px;

            }
        }
//		source.setPixelArray(pixels);
        GImage newImg = new GImage(result);
        return newImg;

    }

    public GImage rotateRight(GImage source) {
        // TODO

        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;
        int[][] result = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                int px = pixels[rows - r - 1][j];
                result[j][r] = px;
            }
        }
//		source.setPixelArray(pixels);
        GImage newImg = new GImage(result);
        return newImg;
    }

    public GImage greenScreen(GImage source) {
        // TODO
        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;

        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                int px = pixels[r][j];
                int red = GImage.getRed(px);
                int green = GImage.getGreen(px);
                int blue = GImage.getBlue(px);

                if (red * 2 < green && (blue * 2 < green)) {
                    int transparentPixel = GImage.createRGBPixel(1, 1, 1, 0);
                    pixels[r][j] = transparentPixel;
                }
            }
        }

        source.setPixelArray(pixels);
        GImage newImg = new GImage(pixels);

        return newImg;
    }

    public GImage equalize(GImage source) { // TODO: equalize not working for all pixel
        // TODO
        int[] hist_1 = lum_Histogram(source);
        int[] hist_2 = cummulative_Hist(hist_1);
        int[] final_hist = result_Hist(hist_2);

        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;
        int[][] result = new int[rows][cols];


        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                int px = pixels[r][j];
                int red = GImage.getRed(px);
                int green = GImage.getGreen(px);
                int blue = GImage.getBlue(px);

                int new_lum = final_hist[computeLuminosity(red, green, blue)];
                int newPixel = GImage.createRGBPixel(new_lum, new_lum, new_lum);
                result[r][j] = newPixel;
            }
        }

        GImage newImg = new GImage(result);

        return newImg;

    }


    private int[] lum_Histogram(GImage source) {
        int[] hist = new int[256];
        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;

        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                int px = pixels[r][j];

                int red = GImage.getRed(px);
                int green = GImage.getGreen(px);
                int blue = GImage.getBlue(px);
                int luminosity = computeLuminosity(red, green, blue);
                hist[luminosity]++;
            }
        }
        return hist;
    }

    private int[] cummulative_Hist(int[] hist) {
        int[] cummulative_hist = new int[256];
        cummulative_hist[0] = hist[0];
        for (int i = 1; i < hist.length; i++) {
            cummulative_hist[i] = hist[i] + cummulative_hist[i - 1];
        }
        return cummulative_hist;
    }

    private int[] result_Hist(int[] cummulative_Hist) {
        int[] final_Hist = new int[256];
        int total_px = cummulative_Hist[255];
        for (int i = 0; i < cummulative_Hist.length; i++) {
            int temp = cummulative_Hist[i];
            int temp_2 = 255 * temp / total_px;
            final_Hist[i] = (int) Math.floor(temp_2);
        }
        return final_Hist;
    }


    public GImage negative(GImage source) {
        // TODO

        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;

        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                int px = pixels[r][j];
                int red = GImage.getRed(px);
                int green = GImage.getGreen(px);
                int blue = GImage.getBlue(px);

                int pixel = GImage.createRGBPixel(255 - red, 255 - green, 255 - blue);
                pixels[r][j] = pixel;
            }
        }
        source.setPixelArray(pixels);
        GImage newImg = new GImage(pixels);

        return newImg;

    }

    public GImage translate(GImage source, int dx, int dy) {
        // TODO

        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;
        int[][] result = new int[rows][cols];

        //Missing Negative Case

        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                if ((j + dx) >= cols && (r + dy) >= rows) {
                    int px = pixels[r][j];
                    result[(r + dy) % rows][(j + dx) % cols] = px;
                } else if ((r + dy) >= rows) {
                    int px = pixels[r][j];
                    result[(r + dy) % rows][j + dx] = px;
                } else if ((j + dx) >= cols) {
                    int px = pixels[r][j];
                    result[r + dy][(j + dx) % cols] = px;
                } else {
                    int px = pixels[r][j];
                    result[r + dy][j + dx] = px;
                }
            }
        }
//		source.setPixelArray(pixels);
        GImage newImg = new GImage(result);
        return newImg;

    }

    public GImage blur(GImage source) {
        // TODO
        int[][] pixels = source.getPixelArray();
        int rows = pixels.length;
        int cols = pixels[0].length;
        int[][] result = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                int pixel = average(pixels, r, j);
                result[r][j] = pixel;
            }
        }

        GImage newImg = new GImage(result);
        return newImg;
    }

    private int average(int[][] array, int location_x, int location_y) {


        int avg_sum_red = 0;
        int avg_sum_blue = 0;
        int avg_sum_green = 0;

        int avg_count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (location_x + i >= 0 && location_x + i < array.length &&
                        location_y + j >= 0 && location_y + j < array[0].length) {
                    int px = array[location_x + i][location_y + j];
                    avg_sum_red += GImage.getRed(px);
                    avg_sum_blue += GImage.getBlue(px);
                    avg_sum_green += GImage.getGreen(px);
                    avg_count++;

                }

            }
        }


        return (GImage.createRGBPixel(avg_sum_red / avg_count, avg_sum_green / avg_count, avg_sum_blue / avg_count));

    }
}
