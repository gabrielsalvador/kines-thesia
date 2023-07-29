import os
import glob

def concatenate_files(folder_path, file_extension, output_file):
    # Use os.walk to traverse through all subdirectories
    for dirpath, dirnames, files in os.walk(folder_path):
        # Use glob to find all files with the given extension
        for filename in glob.glob(os.path.join(dirpath, "*." + file_extension)):
            with open(filename, 'r') as f:
                contents = f.read()

            # Append the contents to the output file
            with open(output_file, 'a') as f:
                f.write(contents)

# Call the function with your parameters
concatenate_files('.', 'java', 'output_file')
