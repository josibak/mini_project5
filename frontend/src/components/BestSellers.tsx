
import { Eye } from 'lucide-react';

const BestSellers = () => {
  const books = [
    {
      id: 1,
      title: "달빛 아래의 서약",
      author: "김소연",
      category: "로맨스",
      views: 1247,
      image: "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400&h=600&fit=crop"
    },
    {
      id: 2,
      title: "미래의 기억",
      author: "박진우",
      category: "SF",
      views: 1156,
      image: "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=600&fit=crop"
    }
  ];

  return (
    <section className="py-20 px-4 sm:px-6 lg:px-8 bg-white">
      <div className="max-w-6xl mx-auto">
        <div className="text-center mb-16">
          <p className="text-amber-700 font-medium mb-4 tracking-wider uppercase">BEST SELLERS</p>
          <h2 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
            이달의 베스트셀러
          </h2>
          <p className="text-lg text-gray-600 max-w-2xl mx-auto">
            독자들이 가장 많이 선택한 화제의 작품들을 만나보세요
          </p>
        </div>
        
        <div className="grid md:grid-cols-2 gap-8 max-w-4xl mx-auto">
          {books.map((book, index) => (
            <div key={book.id} className="group bg-white rounded-lg overflow-hidden hover:shadow-lg transition-shadow duration-300">
              <div className="flex gap-6 p-6">
                <div className="relative flex-shrink-0">
                  <div className="absolute -top-4 -left-4 w-12 h-12 bg-amber-700 text-white rounded-full flex items-center justify-center font-bold text-lg z-10">
                    {index + 1}
                  </div>
                  <img 
                    src={book.image} 
                    alt={book.title}
                    className="w-32 h-48 object-cover rounded-lg"
                  />
                </div>
                
                <div className="flex-1">
                  <div className="mb-2">
                    <span className="inline-block px-3 py-1 bg-gray-100 text-gray-700 text-sm rounded-full">
                      {book.category}
                    </span>
                  </div>
                  
                  <h3 className="text-xl font-medium text-gray-900 mb-2">
                    {book.title}
                  </h3>
                  <p className="text-gray-600 mb-4">{book.author}</p>
                  
                  <div className="flex items-center gap-2 mb-6">
                    <Eye className="w-4 h-4 text-gray-400" />
                    <span className="text-gray-500">{book.views.toLocaleString()}</span>
                  </div>
                  
                  <button className="w-full py-3 bg-amber-700 text-white rounded-lg hover:bg-amber-600 transition-colors duration-200 font-medium">
                    300P로 읽기
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default BestSellers;
