import os
from PIL import Image, ImageEnhance

def apply_overlay(image_path, output_path, color, alpha):
    image = Image.open(image_path).convert("RGBA")
    overlay = Image.new("RGBA", image.size, color + (int(255 * alpha),))
    combined = Image.alpha_composite(image, overlay)
    combined.save(output_path)

def process_icons(input_folder, output_folder):
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)
        
    for file_name in os.listdir(input_folder):
        if file_name.endswith(".png"):
            base_name, ext = os.path.splitext(file_name)
            input_path = os.path.join(input_folder, file_name)

            active_output_path = os.path.join(output_folder, f"{base_name}-active{ext}")
            hover_output_path = os.path.join(output_folder, f"{base_name}-hover{ext}")

            apply_overlay(input_path, active_output_path, (255, 255, 255), 0.5)  # White overlay with 50% opacity
            apply_overlay(input_path, hover_output_path, (128, 128, 128), 0.3)  # Grey overlay with 30% opacity

if __name__ == "__main__":
    input_folder = "./tools"
    output_folder = "./tools"
    process_icons(input_folder, output_folder)