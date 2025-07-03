
import { useState } from 'react';
import { Search, Filter, Grid, List, Eye } from 'lucide-react';
import { Link } from 'react-router-dom';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';

const Collection = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('전체');
  const [viewMode, setViewMode] = useState('grid');

  const categories = ['전체', '로맨스', '판타지', '스릴러', '힐링', '성장', '역사', 'SF'];
  
  const books = [
    {
      id: 1,
      title: "달빛 아래의 서약",
      author: "김소연",
      category: "로맨스",
      description: "두 영혼이 만나 펼치는 아름다운 사랑 이야기",
      image: "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400&h=600&fit=crop",
      views: 127
    },
    {
      id: 2,
      title: "시간의 정원사",
      author: "박민준",
      category: "판타지",
      description: "시간을 다루는 신비한 정원사의 모험",
      image: "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&h=600&fit=crop",
      views: 89
    },
    {
      id: 3,
      title: "잃어버린 기억",
      author: "이지은",
      category: "스릴러",
      description: "기억을 잃은 주인공이 진실을 찾아가는 이야기",
      image: "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400&h=600&fit=crop",
      views: 203
    },
    {
      id: 4,
      title: "작은 카페의 이야기",
      author: "정민호",
      category: "힐링",
      description: "따뜻한 커피와 함께하는 소소한 일상",
      image: "https://images.unsplash.com/photo-1495446815901-a7297e633e8d?w=400&h=600&fit=crop",
      views: 156
    }
  ];

  const filteredBooks = books.filter(book => {
    const matchesSearch = book.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
                         book.author.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesCategory = selectedCategory === '전체' || book.category === selectedCategory;
    return matchesSearch && matchesCategory;
  });

  return (
    <div className="min-h-screen bg-white">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="mb-8">
            <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
              도서 컬렉션
            </h1>
            <p className="text-lg text-gray-600">
              다양한 장르의 특별한 이야기들을 만나보세요
            </p>
          </div>
          
          {/* Search and Filter */}
          <div className="flex flex-col lg:flex-row gap-4 mb-8">
            <div className="flex-1 relative">
              <Search className="absolute left-4 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
              <input
                type="text"
                placeholder="제목, 작가명, 키워드로 검색하세요"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="w-full pl-12 pr-4 py-3 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
              />
            </div>
            
            <div className="flex gap-4">
              <button className="flex items-center gap-2 px-4 py-3 border border-warm-brown-300 rounded-lg hover:bg-warm-brown-50">
                <Filter className="w-5 h-5" />
                필터
              </button>
              <div className="flex border border-warm-brown-300 rounded-lg overflow-hidden">
                <button
                  onClick={() => setViewMode('grid')}
                  className={`p-3 ${viewMode === 'grid' ? 'bg-warm-brown-700 text-white' : 'bg-white text-gray-600 hover:bg-warm-brown-50'}`}
                >
                  <Grid className="w-5 h-5" />
                </button>
                <button
                  onClick={() => setViewMode('list')}
                  className={`p-3 ${viewMode === 'list' ? 'bg-warm-brown-700 text-white' : 'bg-white text-gray-600 hover:bg-warm-brown-50'}`}
                >
                  <List className="w-5 h-5" />
                </button>
              </div>
            </div>
          </div>
          
          {/* Categories */}
          <div className="flex flex-wrap gap-2 mb-8">
            {categories.map((category) => (
              <button
                key={category}
                onClick={() => setSelectedCategory(category)}
                className={`px-4 py-2 rounded-full text-sm transition-colors ${
                  selectedCategory === category
                    ? 'bg-warm-brown-700 text-white'
                    : 'bg-warm-brown-100 text-warm-brown-700 hover:bg-warm-brown-200'
                }`}
              >
                {category}
              </button>
            ))}
          </div>
          
          {/* Books Grid */}
          <div className={`grid gap-6 ${viewMode === 'grid' ? 'md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4' : 'grid-cols-1'}`}>
            {filteredBooks.map((book) => (
              <div key={book.id} className="group bg-white rounded-xl border border-warm-brown-200 overflow-hidden hover:shadow-lg transition-all duration-300">
                {viewMode === 'grid' ? (
                  <>
                    <div className="aspect-[3/4] overflow-hidden">
                      <img 
                        src={book.image} 
                        alt={book.title}
                        className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300"
                      />
                    </div>
                    <div className="p-4">
                      <div className="mb-2">
                        <span className="inline-block px-2 py-1 bg-warm-brown-100 text-warm-brown-700 text-xs rounded-full">
                          {book.category}
                        </span>
                      </div>
                      <h3 className="font-medium text-gray-900 mb-1">{book.title}</h3>
                      <p className="text-sm text-gray-600 mb-2">{book.author}</p>
                      <p className="text-sm text-gray-500 mb-3 line-clamp-2">{book.description}</p>
                      <div className="flex items-center gap-2 text-xs text-gray-500 mb-3">
                        <Eye className="w-3 h-3" />
                        <span>조회 {book.views}</span>
                      </div>
                      <Link to={`/book/${book.id}`}>
                        <Button className="w-full bg-warm-brown-700 hover:bg-warm-brown-800 text-white">
                          도서 보기
                        </Button>
                      </Link>
                    </div>
                  </>
                ) : (
                  <div className="flex gap-4 p-6">
                    <img 
                      src={book.image} 
                      alt={book.title}
                      className="w-24 h-32 object-cover rounded"
                    />
                    <div className="flex-1">
                      <div className="mb-2">
                        <span className="inline-block px-2 py-1 bg-warm-brown-100 text-warm-brown-700 text-xs rounded-full">
                          {book.category}
                        </span>
                      </div>
                      <h3 className="text-lg font-medium text-gray-900 mb-1">{book.title}</h3>
                      <p className="text-gray-600 mb-2">{book.author}</p>
                      <p className="text-gray-500 mb-3">{book.description}</p>
                      <div className="flex items-center justify-between">
                        <div className="flex items-center gap-2 text-sm text-gray-500">
                          <Eye className="w-4 h-4" />
                          <span>조회 {book.views}</span>
                        </div>
                        <Link to={`/book/${book.id}`}>
                          <Button className="bg-warm-brown-700 hover:bg-warm-brown-800 text-white">
                            도서 보기
                          </Button>
                        </Link>
                      </div>
                    </div>
                  </div>
                )}
              </div>
            ))}
          </div>
        </div>
      </main>
      
      <Footer />
    </div>
  );
};

export default Collection;
