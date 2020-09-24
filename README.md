# react-native-seals-native-module
### What is this?

A recycler view with great performance.


![Alt text](https://github.com/JustMoze/customModule/blob/master/recycler.png?raw=true)

Data format -> List of objects

object 👇
...

{
  _id: string,
  rating: number,
  coverImage: string,
  genre: string
}

...

## Getting started

`$ npm install react-native-seals-native-module --save`

### Mostly automatic installation

`$ react-native link react-native-seals-native-module`

## Usage
```javascript

import SealsNativeModule from 'react-native-seals-native-module';

// TODO: What to do with the module?

...

<View style={{width: 500}}>
  <SealsNativeModule
    style={{height: 260, marginRight: 150}}
    data={data}
    onClick={(id) => handleCardClick(id.nativeEvent.id)}
  />
</View>

where handleCardClick ->

function handleMovieCardClick(id: String) {
    // Do something with clicked card id....
}

```
