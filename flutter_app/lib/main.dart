import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Startup Name Generator',
      theme: new ThemeData(
        primaryColor: Colors.redAccent,
      ),
      home: new RandomWords(),
    );
  }
}



class RandomWords extends StatefulWidget{
  @override
   createState() =>new RandomsWordState();
  }


  class RandomsWordState extends State<RandomWords>{
    @override
  final _suggestions=<WordPair>[];
  final _saved=new Set<WordPair>();
  final _biggerFont=const TextStyle(fontSize: 18.0);
  void _pushSaved(){
    Navigator.of(context).push(
      MaterialPageRoute(
        builder:(context){
          final tiles=_saved.map(
              (pair){
                return new ListTile(
                  title: new Text(
                    pair.asPascalCase,
                    style: _biggerFont,
                  ),
                );
              },
          );
          final divided = ListTile
              .divideTiles(
            context: context,
            tiles: tiles,
          )
              .toList();
          return new Scaffold(
            appBar: new AppBar(
              title: new Text('Saved Suggestions'),
            ),
            body: new ListView(children: divided),
          );
        },
      ),
    );
  }


  Widget build (BuildContext context ){
  return new Scaffold(
  appBar: new AppBar(
    title: new Text('Startup Name generastor'),
    actions: <Widget>[
      new IconButton(onPressed: _pushSaved, icon: new Icon(Icons.list))
    ],
  ),
  body: _buildSuggestions(),
  );
  }


  Widget _buildSuggestions(){
    return new ListView.builder(
      padding: const EdgeInsets.all(16.0),
        itemBuilder: (context,i){
        if(i.isOdd) return new Divider();

        final index=i~/2;

        if (index>=_suggestions.length){
          _suggestions.addAll(generateWordPairs().take(10));
    }
        return _buildRow(_suggestions[index]);
    }
    );
  }

  Widget _buildRow(WordPair pair ){
    final alreadysaved=_saved.contains(pair);

    return new ListTile(
      title: new Text(
        pair.asPascalCase,
        style: _biggerFont,
      ),
      trailing: new Icon(
        alreadysaved ? Icons.favorite : Icons.favorite_border,
        color:alreadysaved?Colors.red:null,
      ),
      onTap: (){
        setState(() {
          if(alreadysaved){
            _saved.remove(pair);
          }
          else
            _saved.add((pair));
        });
      },
    );
  }
  }
