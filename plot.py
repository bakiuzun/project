import pandas as pd
import matplotlib.pyplot as plt

# Read the CSV file
df = pd.read_csv('healthData.csv', header=None)

# Set the first column as the index
df.set_index(0, inplace=True)

# Plot the data
df.T.plot(kind='line', legend=True, figsize=(10, 5), title='Health Data')



plt.savefig('plot.png')

# Show the plot
plt.show()