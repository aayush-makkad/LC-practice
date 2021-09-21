public class TrappingRainWater {

    public int trap(int[] height) {
        if(height.length < 2) {
            return 0;
        }
        int totalVolume = 0;
        int[] rightMax = new int[height.length];
        for(int i= height.length-1;i>=0;i--) {
            rightMax[i] = Math.max(height[i], i < height.length - 1 ? rightMax[i + 1] : Integer.MIN_VALUE);
        }
        int leftMax = height[0];
        for(int i=0;i< height.length;i++) {
            leftMax = Math.max(leftMax, height[i]);
            int boundedHeight = Math.min(leftMax, rightMax[i]) - height[i];
            totalVolume+= boundedHeight;
        }
        return totalVolume;
    }

}
